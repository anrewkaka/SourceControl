#! /usr/local/rbenv/shims/ruby
# encoding: utf-8
require 'nokogiri'
require 'open-uri'

# URIの先頭(固定)
BASE_URI = 'http://myphamthailanshop.com/'

# HTML取得のリトライ回数
OPEN_RETRY_COUNT = 5

# AGENTの設定
USER_AGENT = "Mozilla/5.0(Macintosh; Intel Mac OS X 10_7_0) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.854.0 Safari/535.2"

UPLOAD_FOLDER = '/usr/RubyUploads'

# ページ取得
def parse_page(uri, encoding = 'UTF-8')
    page = nil
    retry_count = 0

    begin
        page = Nokogiri.HTML(open(uri, {"User-Agent" => USER_AGENT}), nil, encoding)
    rescue
        if retry_count < OPEN_RETRY_COUNT
            sleep 1
            retry
            retry_count += 1
        else
            raise
        end
    end

    page
end

def random_string(num_of_char)
    o = [('a'..'z')].map { |i| i.to_a }.flatten
    string = (0...num_of_char).map { o[rand(o.length)] }.join

    return string
end

home_page = parse_page(BASE_URI)
link_lst = Array.new()

home_page.xpath('//ul[@id="left-menu"]/li').each { |menu_item|
    top_link = menu_item.xpath('a')
    categoryName = top_link.text
    #        puts categoryName

    sub_menu = menu_item.xpath('ul')
    if sub_menu.empty?
        #            lst_prd_page = parse_page(top_link.attr('href'))
        link_lst << top_link
    end

    sub_menu.xpath('li/a').each { |sub_menu_item_link|
        subCategoryName = sub_menu_item_link.text
        #            puts "xxxxxxxxxxxxx#{subCategoryName}"
        link_lst << sub_menu_item_link
    }
}

def get_prd_lst(link)
    detail_link_lst = Array.new()

    prd_lst_link = "#{BASE_URI}/#{link.attr('href')}"
    prd_lst_page = parse_page(prd_lst_link)

    prd_lst_page.xpath('//*[@id="load"]/div[2]/div/h3/a').each { |detail_link|
        detail_link_lst << detail_link
    }

    loop do
        next_link = nil
        prd_lst_page.xpath('//*[@id="load"]/div[3]/span[@class="prev_next"]/a').each { |prev_next|
            if !prev_next.xpath('img[@alt="Trang sau"]').empty?
                next_link = prev_next
                break
            end
        }

        break if next_link == nil

        next_link = next_link.attr('href')
        if !next_link.empty?
            next_link = "#{BASE_URI}/#{next_link}"

            prd_lst_page = parse_page(next_link)
            prd_lst_page.xpath('//*[@id="load"]/div[2]/div/h3/a').each { |detail_link|
                detail_link_lst << detail_link
            }
        end
    end

    return detail_link_lst
end

def get_fl_ex(src)
    locations = src.split('/')
    extensions = locations.last.split('.')
    file_ex = extensions.last

    return file_ex
end

def download_img(src, fl_nm, out_src = false)
    if out_src == false
        src = "#{BASE_URI}/#{src}"
    end

    open("#{UPLOAD_FOLDER}/#{fl_nm}", 'wb') do |file|
        begin
            file << open(src).read
        rescue
            return false
        end
    end

    return true
end

def output_details(file, category_name, detail_link_lst)
    detail_index = 0
    detail_link_lst.each{ |detail_link|
        detail_index += 1
        detail_link = "#{BASE_URI}/#{detail_link.attr('href')}"
        detail_page = parse_page(detail_link)

        prd_nm = detail_page.xpath('//*[@id="col-mid"]/div[4]/div[1]/div[1]/h1')
        prd_nm = prd_nm.text
        prd_prc = detail_page.xpath('//*[@id="col-mid"]/div[4]/div[1]/div[3]/div[1]/text()')
        prd_prc = prd_prc.text.gsub(/[^0-9]/, '')
        prd_dtl_div = detail_page.xpath('//*[@id="col-mid"]/div[4]/div[2]')
        prd_dtl = prd_dtl_div.text

        puts "---->> product #{detail_index} <<----"

        # get cover
        prd_img_main = detail_page.xpath('//*[@id="mainImg"]')
        prd_img_main = prd_img_main.attr('src').text
        prd_img_main_nm = "shopxuany_#{random_string(10)}.#{get_fl_ex(prd_img_main)}"
        download_img(prd_img_main, prd_img_main_nm)

        # get detail IMG
        prd_img_detail = ""
        detail_page.xpath('//*[@class="product-thumbnail"]/ul/li/div/table').each{ |img|
            img_path = img.xpath('tr/td/a/img').attr('src')
            img_path = img_path.text.sub('/_thumbs', '')
            dtl_img_nm = "shopxuany_#{random_string(10)}.#{get_fl_ex(img_path)}"
            dwn_result = download_img(img_path, dtl_img_nm)

            if dwn_result
                prd_img_detail += "#{dtl_img_nm}→"
            end
        }

        # get content IMG
        prd_dtl_div.xpath('p/img').each{ |content_img|
            img_path = content_img.attr('src')
            prd_content_nm = "shopxuany_#{random_string(10)}.#{get_fl_ex(img_path)}"
            dwn_result = download_img(img_path, prd_content_nm, true)

            if dwn_result
                prd_img_detail += "#{prd_content_nm}→"
            end
        }

        #create detail file
        detail_fl_nm = "#{random_string(20)}.txt";
        open("#{UPLOAD_FOLDER}/#{detail_fl_nm}", 'wb') do |file_dl|
            file_dl.write "#{prd_dtl}\t"
        end

        file.write "#{category_name}\t"
        file.write "#{prd_nm}\t"
        file.write "#{prd_prc}\t"
        file.write "#{detail_fl_nm}\t"
        file.write "#{prd_img_main_nm}\t"
        file.write "#{prd_img_detail}\r\n"

        #TODO:remove
        #        break if prd_nm == 'Son Lì Innisfree'
    }
end

index = 0
file = nil
open('/usr/RubyUploads/AZ_KDL_PRD_000.CSV', 'w:UTF-8') do |file|
    loop {
        link_lst.each { |link|
            puts '---------------------------------------------'
            puts link.text

            detail_link_lst = get_prd_lst(link)
            output_details(file, link.text, detail_link_lst)
            #TODO:remove
            #            break
        }
        break
    }
end
