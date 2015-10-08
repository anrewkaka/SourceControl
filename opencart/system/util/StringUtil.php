<?php
class StringUtil {
    const SEO_LINK_CLASS_CATEGORY = '1';
    const SEO_LINK_CLASS_PRODUCT = '2';

    /**
     * Convert Vietnamese to Latin.
     *
     * @author Lan-NT
     * @param String $str
     * @return String
     */
    public static function unicodeStringFilter($str) {
        $unicode = array (
                'a' => 'á|à|ả|ã|ạ|ă|ắ|ặ|ằ|ẳ|ẵ|â|ấ|ầ|ẩ|ẫ|ậ',
                'd' => 'đ',
                'e' => 'é|è|ẻ|ẽ|ẹ|ê|ế|ề|ể|ễ|ệ',
                'i' => 'í|ì|ỉ|ĩ|ị',
                'o' => 'ó|ò|ỏ|õ|ọ|ô|ố|ồ|ổ|ỗ|ộ|ơ|ớ|ờ|ở|ỡ|ợ',
                'u' => 'ú|ù|ủ|ũ|ụ|ư|ứ|ừ|ử|ữ|ự',
                'y' => 'ý|ỳ|ỷ|ỹ|ỵ',
                'A' => 'Á|À|Ả|Ã|Ạ|Ă|Ắ|Ặ|Ằ|Ẳ|Ẵ|Â|Ấ|Ầ|Ẩ|Ẫ|Ậ',
                'D' => 'Đ',
                'E' => 'É|È|Ẻ|Ẽ|Ẹ|Ê|Ế|Ề|Ể|Ễ|Ệ',
                'I' => 'Í|Ì|Ỉ|Ĩ|Ị',
                'O' => 'Ó|Ò|Ỏ|Õ|Ọ|Ô|Ố|Ồ|Ổ|Ỗ|Ộ|Ơ|Ớ|Ờ|Ở|Ỡ|Ợ',
                'U' => 'Ú|Ù|Ủ|Ũ|Ụ|Ư|Ứ|Ừ|Ử|Ữ|Ự',
                'Y' => 'Ý|Ỳ|Ỷ|Ỹ|Ỵ'
        );
        foreach($unicode as $nonUnicode => $uni) {
            $str = preg_replace("/($uni)/i", $nonUnicode, $str);
        }

        return $str;
    }

    /**
     * Convert name to seo_name.
     *
     * @author Lan-NT
     * @param String $class
     * @param String $name
     * @return string
     */
    public static function getNameToSeoLink($class, $name) {
        $seoNm = '';
        $unicodeFilteredStr = StringUtil::unicodeStringFilter($name);
        $lowerNm = strtolower($unicodeFilteredStr);
        $arrLowerNm = explode(' ', $lowerNm);

        foreach($arrLowerNm as $lowerNmWord) {
            $seoNm .= $lowerNmWord;
            $seoNm .= '-';
        }

        $seoNm = substr($seoNm, 0, strlen($seoNm) - 1);

        if(StringUtil::SEO_LINK_CLASS_PRODUCT == $class) {
            $seoNm .= '.html';
        }

        return $seoNm;
    }
}