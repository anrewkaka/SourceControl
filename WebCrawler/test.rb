#! /usr/local/rbenv/shims/ruby
# -*- coding: utf-8 -*-
require 'nokogiri'
require 'open-uri'



# URIの先頭(固定)
BASE_URI = 'http://myphamthailanshop.com/'

# HTML取得のリトライ回数
OPEN_RETRY_COUNT = 5

# AGENTの設定
USER_AGENT = "Mozilla/5.0(Macintosh; Intel Mac OS X 10_7_0) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.854.0 Safari/535.2"


# ページ取得
def parse_page(uri, encoding = nil)
  page = nil
  retry_count = 0

  begin
#    page = Nokogiri.HTML(open(uri, {:proxy => PROXY_ADR, "User-Agent" => USER_AGENT}),
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


home_page = parse_page(BASE_URI)
index = 0
file = nil
open('AZ_KDL_PRD_000.TSV', 'w') do |file|
  loop {
	home_page.xpath('//ul[@id="left-menu"]/li').each { |menu_item|
		puts '---------------------------'
		puts index += 1
		top_link = menu_item.xpath('a')
		
		categoryName = top_link.text
		puts categoryName
		
		target = top_link.attr('target')
		if target <=> '_self'
			lst_prd_page = parse_page(top_link.attr('href'))
			puts
		end
		
		sub_menu = menu_item.xpath('ul')
		sub_menu.xpath('li/a').each { |sub_menu_item_link|
			
			subCategoryName = sub_menu_item_link.text
			puts subCategoryName
		}
		
	}
	break
  }
end


