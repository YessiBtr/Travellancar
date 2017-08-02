package develops.indotravel.stud11314031.api;

import java.util.List;

import develops.indotravel.stud11314031.model.Request;
import develops.indotravel.stud11314031.model.Wisata;
import develops.indotravel.stud11314031.model.WisataKota;
import develops.indotravel.stud11314031.model.WisataTempat;

/**
 * Created by Vranata on 15/05/2017.
 */

public class ResponseWisataAPI {

    private String error;

    private List<WisataTempat> wisata;
    private List<WisataKota> kota;
    private List<Wisata> provinsi;
    private List<Request> request;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<WisataTempat> getWisata() {
        return wisata;
    }

    public void setWisata(List<WisataTempat> wisata) {
        this.wisata = wisata;
    }

    public List<WisataKota> getKota() {
        return kota;
    }

    public void setKota(List<WisataKota> kota) {
        this.kota = kota;
    }

    public List<Wisata> getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(List<Wisata> provinsi) {
        this.provinsi = provinsi;
    }

    public List<Request> getRequest() {
        return request;
    }

    public void setRequest(List<Request> request) {
        this.request = request;
    }
}
