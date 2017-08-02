package develops.indotravel.stud11314031.model;

/**
 * Created by Vranata on 11/05/2017.
 */

public class WisataTempat {
    private int    id;
    private int kota_id;
    private String nama_tempat;
    private String keterangan;
    private String sumber_gambar;

    public WisataTempat(int id, int kota_id, String nama_tempat, String keterangan, String sumber_gambar) {
        this.id = id;
        this.kota_id = kota_id;
        this.nama_tempat = nama_tempat;
        this.keterangan = keterangan;
        this.sumber_gambar = sumber_gambar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKota_id() {
        return kota_id;
    }

    public void setKota_id(int kota_id) {
        this.kota_id = kota_id;
    }

    public String getNama_tempat() {
        return nama_tempat;
    }

    public void setNama_tempat(String nama_tempat) {
        this.nama_tempat = nama_tempat;
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
}



