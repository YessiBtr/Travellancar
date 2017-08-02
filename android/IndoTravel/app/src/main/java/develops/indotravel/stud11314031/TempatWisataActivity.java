package develops.indotravel.stud11314031;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

import develops.indotravel.stud11314031.adapter.WisataTempatAdapter;
import develops.indotravel.stud11314031.api.ResponseWisataAPI;
import develops.indotravel.stud11314031.api.RestWisataClient;
import develops.indotravel.stud11314031.api.WisataAPI;
import develops.indotravel.stud11314031.model.Request;
import develops.indotravel.stud11314031.model.WisataTempat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TempatWisataActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WisataTempatAdapter adapter;
    private List<WisataTempat> wisataList;
    private AdView mAdView;
    WisataAPI wisataAPI;
    private List<Request> wisataRequest;

    int id_provinsi = 0;
    int id_kota = 0;
    String username = "siregaraditya";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tempat_wisata);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        wisataList = new ArrayList<>();

        id_provinsi = getIntent().getIntExtra("id_provinsi", 0);
        id_kota = getIntent().getIntExtra("id_kota", 0);

        Callback<ResponseWisataAPI> hasilWisata = new Callback<ResponseWisataAPI>() {
            @Override
            public void onResponse(Call<ResponseWisataAPI> call, Response<ResponseWisataAPI> response) {
                System.out.println(call.request().url().toString());
                if (response.isSuccessful()) {
                    //menampung isi dari List<ModelHasilBola> dariResponseAPI
                    wisataList = response.body().getWisata();
                    adapter = new WisataTempatAdapter(TempatWisataActivity.this, wisataList);
                    adapter.setId_kota(id_kota);
                    adapter.setId_provinsi(id_provinsi);
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(TempatWisataActivity.this, 1);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.addItemDecoration(new TempatWisataActivity.GridSpacingItemDecoration(2, dpToPx(10), true));
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } else {
                    Log.e("onResponse failure", "Code: " + response.code() + " , Message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseWisataAPI> call, Throwable t) {
                Log.e("Akses ke server gagal", "Code: " + t.getMessage() + " , Message: " + t.getCause());
                // TODO Auto-generated method stub
                //              Toast.makeText(getContext(), "AKSES KE SERVER GAGAL "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        };

        wisataAPI = RestWisataClient.get();
        Call<ResponseWisataAPI> callHasilBola = wisataAPI.getWisata(id_kota + "");
        callHasilBola.enqueue(hasilWisata);

        mAdView = (AdView) findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
            }

            @Override
            public void onAdClosed() {
                Toast.makeText(getApplicationContext(), "Ad is closed!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Toast.makeText(getApplicationContext(), "Ad failed to load! error code: " + errorCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
                Toast.makeText(getApplicationContext(), "Ad left application!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }
        });

        mAdView.loadAd(adRequest);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(TempatWisataActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_request, null);
                Button mAdd = (Button) mView.findViewById(R.id.btnRequest);
                Button mCanc = (Button) mView.findViewById(R.id.btnCancel);
                final TextView namaTempat = (TextView) mView.findViewById(R.id.inputNamaTempat);
                final TextView keterangan = (TextView) mView.findViewById(R.id.inputKeterangan);
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.setTitle("Request Tempat Wisata");
                dialog.show();
                mAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!namaTempat.getText().toString().equals("") && !keterangan.getText().toString().equals("")) {
                            Callback<ResponseWisataAPI> setRequest = new Callback<ResponseWisataAPI>() {
                                @Override
                                public void onResponse(Call<ResponseWisataAPI> call, Response<ResponseWisataAPI> response) {
                                    System.out.println(call.request().url().toString());
                                    if (response.isSuccessful()) {
                                        //menampung isi dari List<ModelHasilBola> dariResponseAPI
                                        wisataRequest = response.body().getRequest();
                                    } else {
                                        Log.e("onResponse failure", "Code: " + response.code() + " , Message: " + response.message());
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseWisataAPI> call, Throwable t) {
                                    Log.e("Akses ke server gagal", "Code: " + t.getMessage() + " , Message: " + t.getCause());
                                }
                            };
                            wisataAPI = RestWisataClient.get();
                            Call<ResponseWisataAPI> callHasilBola = wisataAPI.setRequest(id_kota + "", namaTempat.getText().toString(), keterangan.getText().toString(), username);
                            callHasilBola.enqueue(setRequest);
                        }
                        dialog.dismiss();
                    }
                });
                mCanc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    private void prepareAlbums() {

        int[] covers = new int[]{
                R.drawable.album1,
                R.drawable.album2,
                R.drawable.album3,
                R.drawable.album4,
                R.drawable.album5,
                R.drawable.album6,
                R.drawable.album7,
                R.drawable.album8,
                R.drawable.album9,
                R.drawable.album10,
                R.drawable.album11
        };

        String[] provinsi = new String[]{
                "Asahan Kab (Kota Kisaran)",
                "Batu Bara",
                "Binjai",
                "Dairi Kab (Sidikalang)",
                "Deli Serdang kab (Lubuk Pakam)",
                "Humbang Hasundutan Kab",
                "Lampung",
                "DKI Jakarta",
                "Yogyakarta",
                "Jawa Timur",
                "Jawa Barat"
        };

        WisataTempat wisata;
        for (int i = 0; i < 11; i++) {
            wisata = new WisataTempat(1 + i, 2 + i, provinsi[i], "hasdas", "wisata ini bareads asudba asidbas aisdubas aisudbasi asiudba .....");
            wisataList.add(wisata);
        }
        adapter.notifyDataSetChanged();
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
}
