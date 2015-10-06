<?php
class ModelCatalogColorskin extends Model {
 
    public function chkactive() {
        $this->db->query("CREATE TABLE IF NOT EXISTS " . DB_PREFIX . "colorskin (
		  `skin_id` int(11) NOT NULL AUTO_INCREMENT,
		  `skinname` varchar(64) NOT NULL,
		  `status` int(3) NOT NULL,
		   PRIMARY KEY (`skin_id`)
		 )  ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
		");

		$sql = "SELECT * FROM " . DB_PREFIX . "colorskin" . " where status= '1' ";
		$query = $this->db->query($sql);
		return $query->row; 
        
	    $this->db->query("DELETE  FROM " . DB_PREFIX . "colorskin  ");

	}

    public function cleanskin() { 
		$this->db->query("DELETE  FROM " . DB_PREFIX . "colorskin  ");
    } 

    public function Initial($initial) {
         
         $this->db->query("UPDATE  " . DB_PREFIX . "colorskin SET  status ='1'
		  where skinname = '" . $this->db->escape($initial)   . "'
		
		");

      } 

	public function Insertskin($skinname) {
         
         $this->db->query("INSERT INTO  " . DB_PREFIX . "colorskin SET 
		  skinname = '" . $this->db->escape($skinname)   . "'
		
		");

      } 
    
	public function Loadskin() {
         
        $query = $this->db->query("SELECT * FROM  " . DB_PREFIX . "colorskin   ");
        return $query->rows; 
   } 
   

   public function checkstatus($skin_id) {
         
        $query = $this->db->query("SELECT * FROM  " . DB_PREFIX . "colorskin  
		where skin_id = '" . $skin_id  . "'
		");
        return $query->row; 
   } 

     
	public function updateskin($data,$skin_id) {
         
         $this->db->query("UPDATE  " . DB_PREFIX . "colorskin set status ='0'  ");
		 $this->db->query("UPDATE  " . DB_PREFIX . "colorskin set status =" . $data['skincolor_status'] ." where   skin_id = '" . $this->db->escape($skin_id)   . "' ");
        
   } 



}