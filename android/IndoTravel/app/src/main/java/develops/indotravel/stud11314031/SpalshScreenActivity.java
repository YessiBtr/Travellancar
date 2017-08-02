package develops.indotravel.stud11314031;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import develops.indotravel.stud11314031.api.ResponseWisataAPI;
import develops.indotravel.stud11314031.api.RestWisataClient;
import develops.indotravel.stud11314031.api.WisataAPI;
import develops.indotravel.stud11314031.model.Wisata;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpalshScreenActivity extends AppCompatActivity {

    WisataAPI wisataAPI;
    Realm realm = null;
    List<Wisata> wisataList;
    Wisata w;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh_screen);

        TextView image = (TextView) findViewById(R.id.textSplash);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        image.startAnimation(animation1);

        try {
            realm = Realm.getDefaultInstance();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_LONG).show();
        }

        Callback<ResponseWisataAPI> hasilProvinsi = new Callback<ResponseWisataAPI>() {
            @Override
            public void onResponse(Call<ResponseWisataAPI> call, Response<ResponseWisataAPI> response) {
                if (response.isSuccessful()) {

                    //menampung isi dari List<ModelHasilBola> dariResponseAPI
                    wisataList = response.body().getProvinsi();
                    try {
                        for(int i=0;i<wisataList.size();i++){
                            w = wisataList.get(i);
                            realm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    //bagian ini untuk menambahkan data Pemain baru
                                    Wisata wisata = realm.createObject(Wisata.class,w.getId());
                                    wisata.setJumlah_kota(w.getJumlah_kota());
                                    wisata.setKeterangan(w.getKeterangan());
                                    wisata.setNama_provinsi(w.getNama_provinsi());
                                    wisata.setSumber_gambar(w.getSumber_gambar());
                                    //pemain.setNamaBelakang(namaBelakang);
                                    System.out.println("Data Sudah Disimpan "+w.getNama_provinsi());
                                }
                            });
                        }
                    }catch (Exception e){
//                        Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                    finally {
                        //realm.close();
                    }
                } else {
//                    Log.e("onResponse failure", "Code: " + response.code() + " , Message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseWisataAPI> call, Throwable t)
            {
                Log.e("Akses ke server gagal", "Code: " + t.getMessage() + " , Message: " + t.getCause());
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "AKSES KE SERVER GAGAL "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        };

        //consuming web service here
        wisataAPI = RestWisataClient.get();
        Call<ResponseWisataAPI> callHasilBola = wisataAPI.getProvinsi();
        callHasilBola.enqueue(hasilProvinsi);
        wisataAPI.getProvinsi();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                final Intent mainIntent = new Intent(getApplicationContext(), WisataKotaActivity.MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, 3000); //delay 3 detik

    }
}
