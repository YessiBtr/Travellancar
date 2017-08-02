<?php
if (PHP_SAPI == 'cli-server') {
    // To help the built-in PHP dev server, check if the request was actually for
    // something which should probably be served as a static file
    $url  = parse_url($_SERVER['REQUEST_URI']);
    $file = __DIR__ . $url['path'];
    if (is_file($file)) {
        return false;
    }
}

require __DIR__ . '/../vendor/autoload.php';
require __DIR__ . '/../db/DbHandler.php';

session_start();

// Instantiate the app
$app = new \Slim\App();

// Add route callbacks
$app->get('/', function ($request, $response, $args) {
    return $response->withStatus(200)->write('Hello World!');
});

$app->get('/hello', function ($request, $response, $args) {
    return $response->withStatus(200)->write('Hello World ssss!');
});

function echoRespnse($status_code, $response) {
    $app = \Slim\App();    

    // Http response code
    $app->status($status_code);        
    // setting response content type to json
    $app->contentType('application/json');
    //print_r($response);
    echo json_encode($response);
}

$app->post('/provinsi',  function ($request, $response, $args) {    
    $db = new DbHandler();
    //print_r($db);    
    // fetching all hasil
    $result = $db->getProvinsi();
    //print_r($result);
    $results["error"] = false;
    $results["provinsi"] = array();
    // looping through result and preparing materi array
    while ($strData = $result->fetch_assoc()) {
        $tmp = array();
        //print_r($strData["nama_provinsi"]);
        $tmp["id"] = utf8_encode($strData["id"]);
        $tmp["nama_provinsi"] = utf8_encode($strData["nama_provinsi"]);
        $tmp["keterangan"] = utf8_encode($strData["keterangan"]);
        $tmp["sumber_gambar"] = utf8_encode($strData["sumber_gambar"]);
        $tmp["jumlah_kota"] = utf8_encode($strData["jumlah_kota"]);
        array_push($results["provinsi"], $tmp);
    }
    
    return $response->withJSON($results);
});

$app->post('/request/{kota_id}/{nama_tempat}/{keterangan}/{user}',  function ($request, $response, $args) {    
    $db = new DbHandler();
    //print_r($db);    
    // fetching all hasil
    $kota_id = $request->getAttribute('kota_id');
    $nama_tempat = $request->getAttribute('nama_tempat');
    $keterangan = $request->getAttribute('keterangan');
    $user = $request->getAttribute('user');
    
    $result = $db->setRequest($kota_id,$nama_tempat,$keterangan,$user);
    //print_r($result);
    $results["error"] = false;
    $results["request"] = array();
    return $response->withJSON($results);
});

$app->post('/kota/{id}',  function ($request, $response, $args) {    
    $db = new DbHandler();
    
    $id = $request->getAttribute('id');
    
    $result = $db->getKota($id);

    //print_r($result);
    $results["error"] = false;
    $results["kota"] = array();
    

    // looping through result and preparing materi array        
    while ($strData = $result->fetch_assoc()) {
        $tmp = array();    
        //print_r($strData["nama_provinsi"]);
        $tmp["id"] = utf8_encode($strData["id"]);
        $tmp["provinsi_id"] = utf8_encode($strData["provinsi_id"]);
        $tmp["nama_kota"] = utf8_encode($strData["nama_kota"]);
        $tmp["keterangan"] = utf8_encode($strData["keterangan"]);
        $tmp["sumber_gambar"] = utf8_encode($strData["sumber_gambar"]);
        $tmp["jumlah_wisata"] = utf8_encode($strData["jumlah_wisata"]);
        array_push($results["kota"], $tmp);
    }
    
    return $response->withJSON($results);
});

$app->post('/wisata/{id}',  function ($request, $response, $args) {    
    $db = new DbHandler();    
    $id = $request->getAttribute('id');    
    $result = $db->getWisata($id);
        
    //print_r($result);
    $results["error"] = false;
    $results["wisata"] = array();
    
    // looping through result and preparing materi array        
    while ($strData = $result->fetch_assoc()) {
        $tmp = array();    
        //print_r($strData["nama_provinsi"]);
        $tmp["id"] = utf8_encode($strData["id"]);
        $tmp["kota_id"] = utf8_encode($strData["kota_id"]);
        $tmp["nama_tempat"] = utf8_encode($strData["nama_tempat"]);
        $tmp["keterangan"] = utf8_encode($strData["keterangan"]);
        $tmp["sumber_gambar"] = utf8_encode($strData["sumber_gambar"]);
        array_push($results["wisata"], $tmp);
    }
    
    return $response->withJSON($results);
});


$app->post('/berita', function ($request, $response, $args){
    $url = 'https://api.cognitive.microsoft.com/bing/v5.0/news/search?q=travel+indonesia&count=50&offset=0&mkt=en-ID&safeSearch=Moderate';
    $responses = \Httpful\Request::get($url)->addHeader('Ocp-Apim-Subscription-Key', 'ddc6d5ceb87b468d9ec9ae1d99cc8da6')->send();
    $result = json_decode($responses);
    $daftarBerita = $result->value;
   
    $results[] = array();
    $results["error"] = false;
    $results["hasil"] = array();
    
    foreach ($daftarBerita as $berita) {
            if (isset($berita->image)) {
                $tmp = array();
                $tmp["uri"] = $berita->url;
                $tmp["image"] = $berita->image->thumbnail->contentUrl;
                $tmp["name"] = $berita->name;
                $tmp["description"] = $berita->description;
                $tmp["sumber"] = $berita->provider[0]->name;
                $tmp["date"] = $berita->datePublished;                
                array_push($results["hasil"], $tmp);      
            }            
            $i+=1;
    }    
    
    return $response->withJSON($results);
});
    
// Run application
$app->run();