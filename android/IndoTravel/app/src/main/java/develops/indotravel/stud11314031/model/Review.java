package develops.indotravel.stud11314031.model;

/**
 * Created by Vranata on 16/05/2017.
 */

public class Review{

    String email;
    String komentar;
    String nama;
    float rating;
    String tanggal;

    public Review() {

    }

    public Review(String email, String komentar, String nama, float rating, String tanggal) {
        this.email = email;
        this.komentar = komentar;
        this.nama = nama;
        this.rating = rating;
        this.tanggal = tanggal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }
}
