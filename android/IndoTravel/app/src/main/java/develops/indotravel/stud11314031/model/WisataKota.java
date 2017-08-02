package develops.indotravel.stud11314031.model;

/**
 * Created by Vranata on 11/05/2017.
 */

public class WisataKota {

    private int id;
    private int provinsi_id;
    private String nama_kota;
    private String keterangan;
    private String sumber_gambar;
    private int jumlah_wisata;

    public WisataKota(int id, String kota, String keterangan, String image, int jumlahTempatWisata) {
        this.id = id;
        this.nama_kota = kota;
        this.keterangan = keterangan;
        this.sumber_gambar = image;
        this.jumlah_wisata = jumlahTempatWisata;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKota() {
        return nama_kota;
    }

    public void setKota(String kota) {
        this.nama_kota = kota;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getImage() {
        return sumber_gambar;
    }

    public void setImage(String image) {
        this.sumber_gambar = image;
    }

    public int getJumlahTempatWisata() {
        return jumlah_wisata;
    }

    public void setJumlahTempatWisata(int jumlahTempatWisata) {
        this.jumlah_wisata = jumlahTempatWisata;
    }
}
