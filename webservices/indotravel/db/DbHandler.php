<?php

class DbHandler {

    private $conn;

    function __construct() {
        require_once dirname(__FILE__) . './DbConnect.php';
        // opening db connection
        $db = new DbConnect();
        $this->conn = $db->connect();
    }

    /**
     * Fetching hasil liga mingguan
     */
    public function getProvinsi() {
        $stmt = $this->conn->prepare("SELECT p.* ,(SELECT COUNT(*) FROM kota  WHERE kota.provinsi_id = p.id ) AS jumlah_kota FROM provinsi p ORDER BY jumlah_kota DESC");
        $stmt->execute();
        $tasks = $stmt->get_result();
        $stmt->close();
        return $tasks;
    }
	
	public function setRequest($kota_id,$nama_tempat,$keterangan,$user) {        
        $string = "INSERT  INTO `request`(`kota_id`,`nama_tempat`,`keterangan`,`status`,`user`) VALUES (".$kota_id.",'".$nama_tempat."','".$keterangan."',0,'".$user."')";
        $stmt = $this->conn->prepare($string);                
        try{
           $stmt->execute();   
           $tasks = $stmt->get_result();
           $stmt->close();
           return $tasks;
        } catch (Exception $ex) {
            echo $stmt . "<br>" . $ex->getMessage();
                die($ex->getMessage());
        }    
    }
    
	
    
    public function getKota($id) {
        $abc = "SELECT k.* ,(SELECT COUNT(*) FROM wisata w WHERE w.kota_id = k.id ) AS jumlah_wisata FROM kota k WHERE k.provinsi_id = ".$id." ORDER BY jumlah_wisata DESC";
        $stmt = $this->conn->prepare($abc);
        $stmt->execute();
        $tasks = $stmt->get_result();
        $stmt->close();
        return $tasks;
    }
    
    public function getWisata($id) {
        $abc = "SELECT w.* FROM wisata w WHERE w.kota_id = ".$id;        
        $stmt = $this->conn->prepare($abc);
        $stmt->execute();
        $tasks = $stmt->get_result();
        $stmt->close();
        return $tasks;
    }
    

}

?>