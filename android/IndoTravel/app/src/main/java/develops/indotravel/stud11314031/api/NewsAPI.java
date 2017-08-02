package develops.indotravel.stud11314031.api;

import retrofit2.Call;
import retrofit2.http.POST;

public interface NewsAPI {

    @POST("/pam_indo_travel/indotravel/public/berita")
    Call<ResponseAPI> getHasilBerita();
}