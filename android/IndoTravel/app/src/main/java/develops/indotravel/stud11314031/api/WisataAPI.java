package develops.indotravel.stud11314031.api;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Vranata on 15/05/2017.
 */

public interface WisataAPI {
    @POST("/pam_indo_travel/indotravel/public/provinsi")
    Call<ResponseWisataAPI> getProvinsi();

    @POST("/pam_indo_travel/indotravel/public/kota/{id}")
    Call<ResponseWisataAPI> getKota(
            @Path("id") String id
    );

    @POST("/pam_indo_travel/indotravel/public/request/{kota_id}/{nama_tempat}/{keterangan}/{user}")
    Call<ResponseWisataAPI> setRequest(
            @Path("kota_id") String kota_id,
            @Path("nama_tempat") String nama_tempat,
            @Path("keterangan") String keterangan,
            @Path("user") String user
    );

    @POST("/pam_indo_travel/indotravel/public/wisata/{id}")
    Call<ResponseWisataAPI> getWisata(
            @Path("id") String id
    );
}
