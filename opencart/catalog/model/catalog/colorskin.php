<?php
class ModelCatalogColorskin extends Model {

  public function Loadskin() {

        $this->db->query("CREATE TABLE IF NOT EXISTS " . DB_PREFIX . "colorskin (
		  `skin_id` int(11) NOT NULL AUTO_INCREMENT,
		  `skinname` varchar(64) NOT NULL,
		  `status` int(3) NOT NULL,
		   PRIMARY KEY (`skin_id`)
		 )  ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
		");


        $query = $this->db->query("SELECT *  FROM  " . DB_PREFIX . "colorskin  where status = 1
		 ");
		return $query->row;
    
       } 


 }

 
?>