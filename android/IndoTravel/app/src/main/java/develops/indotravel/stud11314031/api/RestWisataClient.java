package develops.indotravel.stud11314031.api;

/**
 * Created by Vranata on 14/05/2017.
 */

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Roy Deddy Tobing on 4/18/2017.
 */
public class RestWisataClient {

    private static WisataAPI REST_CLIENT;

    static {
        //dieksekusi sebelum constructor, tapi hanya sekali untuksemua instans
        setupRestClient();
    }

    private RestWisataClient() {}
    public static WisataAPI get() {
        return REST_CLIENT;
    }

    private static void setupRestClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        REST_CLIENT = retrofit.create(WisataAPI.class);
    }
}
