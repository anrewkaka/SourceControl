#! /usr/local/rbenv/shims/ruby
# -*- coding: utf-8 -*-
require 'nokogiri'
require 'open-uri'

if ARGV.empty?
  # 引数がない場合は終了する
  puts "specify uri"
  exit
end

# URIの先頭(固定)
BASE_URI = 'http://www.amazon.co.jp'

# HTML取得のリトライ回数
OPEN_RETRY_COUNT = 5

# AGENTの設定
USER_AGENT = "Mozilla/5.0(Macintosh; Intel Mac OS X 10_7_0) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.854.0 Safari/535.2"

# proxyの設定
PROXY_ADR = "http://172.17.192.16:8080/"

# ページ取得
def parse_page(uri, encoding = nil)
  page = nil
  retry_count = 0

  begin
#    page = Nokogiri.HTML(open(uri, {:proxy => PROXY_ADR, "User-Agent" => USER_AGENT}),
    page = Nokogiri.HTML(open(uri, {:proxy => 'http://172.17.192.16:8080/', "User-Agent" => USER_AGENT}),
                         nil, encoding)
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

index = 0 # 連番

# 出版社の検索結果ページ
uri = ARGV[0]
search_page = parse_page(uri)

file = nil
open('AZ_KDL_PRD_000.TSV', 'w') do |file|
  loop {
    # 商品詳細ページへのリンク単位に以下を実行する
    search_page.xpath("//a[@class='a-link-normal s-access-detail-page  a-text-normal']").each { |detail_page_anchor|
      
      # 商品詳細ページのURI
      uri = detail_page_anchor.attr('href')
      
      # 商品詳細ページの取得→解析
      detail_page = parse_page(uri, 'CP932')
      puts '------------------------------------'
      puts index += 1
      
      # URI
      puts uri
      
      buying = detail_page.xpath("//*[@id='divsinglecolumnminwidth']/div[@class='buying']")
      
      # 商品名(kindleオリジナル)
      original_product_name = buying.xpath("id('btAsinTitle')").text
      
      # 商品名
      puts product_name = original_product_name.gsub(/[ ]*\[Kindle版\]$/, "")
      
      # 著者
      author = ''
      buying.xpath("a").each { |anchor|
        author += anchor.text + ','
      }
      puts author.chop!
      
      # 価格
      price = ''
      tbody = detail_page.xpath("//*[@id='priceBlock']/table")
      tbody.xpath("tr").each { |tr|
        if tr.xpath("td[1]").text =~ /Kindle.*価格:/
          price = tr.xpath("td[2]").text.gsub(/[^0-9]/, "")
          break
        end
      }
      puts price
      
      # 出版社,販売開始日,ASIN
      publisher_name = ""
      published_at = nil
      asin = ""
      text = detail_page.xpath("//*[@id='productDetailsTable']//tr/td/div/ul/li").each { |li|
        if li.xpath("b").text =~ /出版社/
          match = li.text.match(/^([^:]+)\:( *)([^;(]+)(.*)\((\d{4}\/\d{1,2}\/\d{1,2})\)$/)
          publisher_name = $3.strip
          published_at = $5
        elsif li.xpath("b").text =~ /ASIN/
          match = li.text.match(/^([^:]+)\:( *)([^; ]+)(.*)$/)
          asin = $3
        end
      }
      puts publisher_name
      puts published_at
      puts asin
      
      # ISBN
      isbn = "" 
      match = detail_page.text.match(/印刷版（ISBN ([0-9]+)）に基/)
      if $1.nil?
        # ISBNが無い商品
        puts 'no isbn'
      else
        puts isbn = $1
      end
      
      file.write "#{asin}\t"
      file.write "#{isbn}\t"
      file.write "#{product_name}\t"
      file.write "#{price}\t"
      file.write "#{published_at}\t"
      file.write "#{publisher_name}\t"
      file.write "#{author}\t"
      file.write "#{uri}\n"
    
    }
    
    # 次のページリンク
    next_page_anchor = search_page.xpath("//a[@id='pagnNextLink']")
    if next_page_anchor.empty?
      # 次のページがなければ終了
      break
    else
      # 次のページを取得する
      uri = BASE_URI + next_page_anchor.attr('href')
      search_page = parse_page(uri);
    end
}
end
