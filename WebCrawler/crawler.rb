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

puts random_string(5)
puts random_string(10)
puts random_string(15)