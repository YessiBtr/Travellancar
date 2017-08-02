package develops.indotravel.stud11314031.api;

/**
 * Created by Vranata on 14/05/2017.
 */

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient{

    private static NewsAPI REST_CLIENT;

    static {
        //dieksekusi sebelum constructor, tapi hanya sekali untuksemua instans
        setupRestClient();
    }

    private RestClient() {}
    public static NewsAPI get() {
        return REST_CLIENT;
    }

    private static void setupRestClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        REST_CLIENT = retrofit.create(NewsAPI.class);
    }
}
