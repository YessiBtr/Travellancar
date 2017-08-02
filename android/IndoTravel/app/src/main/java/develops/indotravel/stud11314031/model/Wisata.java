package develops.indotravel.stud11314031.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Vranata on 11/05/2017.
 */

public class Wisata extends RealmObject {

    @PrimaryKey
    private int id;
    private String nama_provinsi;
    private String keterangan;
    private String sumber_gambar;
    private int jumlah_kota;

    public Wisata(){

    }

    public Wisata(int id, String provinsi, String keterangan, String image,int jumlahKotaWisata) {
        this.id = id;
        this.nama_provinsi = provinsi;
        this.keterangan = keterangan;
        this.sumber_gambar = image;
        this.jumlah_kota = jumlahKotaWisata;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama_provinsi() {
        return nama_provinsi;
    }

    public void setNama_provinsi(String nama_provinsi) {
        this.nama_provinsi = nama_provinsi;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getSumber_gambar() {
        return sumber_gambar;
    }

    public void setSumber_gambar(String sumber_gambar) {
        this.sumber_gambar = sumber_gambar;
    }

    public int getJumlah_kota() {
        return jumlah_kota;
    }

    public void setJumlah_kota(int jumlah_kota) {
        this.jumlah_kota = jumlah_kota;
    }
}
