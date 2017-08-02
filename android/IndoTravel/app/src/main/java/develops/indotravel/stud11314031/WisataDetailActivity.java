package develops.indotravel.stud11314031;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import develops.indotravel.stud11314031.adapter.TabFragmentPagerDetailAdapter;
import develops.indotravel.stud11314031.model.Review;

public class WisataDetailActivity extends AppCompatActivity {

    private ViewPager pager;
    private TabLayout tabs;
    InterstitialAd mInterstitialAd;

    private FirebaseDatabase mFireBaseDatabase;
    private DatabaseReference mDatabaseReference;

    int id_provinsi=0;
    int id_kota=0;
    int id_tempat=0;
    String Nama= "Aditya Pranata";
    String username= "siregaraditya";
    String email="siregaraditya@gmail.com";
    String tanggal="16/05/2017";
    float rating;
    String komentar;
    String sumber_gambar;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wisata_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initFirebase();

        sumber_gambar =   getIntent().getStringExtra("sumber_gambar");
        name         =   getIntent().getStringExtra("nama_tempat");
        id_provinsi  =  getIntent().getIntExtra("id_provinsi",0);
        id_kota  =  getIntent().getIntExtra("id_kota",0);
        id_tempat = getIntent().getIntExtra("id_wisata",0);

        getSupportActionBar().setTitle(name);

        try {
            Glide.with(this).load(sumber_gambar).into((ImageView) findViewById(R.id.backdrop));
        }catch (Exception e) {
            e.printStackTrace();
        }

        pager = (ViewPager)findViewById(R.id.pager);
        tabs = (TabLayout)findViewById(R.id.tabs);

        //set object adapter kedalam ViewPager
        pager.setAdapter(new TabFragmentPagerDetailAdapter(getSupportFragmentManager()));

        //Manimpilasi sedikit untuk set TextColor pada Tab
        tabs.setTabTextColors(getResources().getColor(R.color.colorPrimaryDark), getResources().getColor(android.R.color.white));

        //set tab ke ViewPager
        tabs.setupWithViewPager(pager);

        //konfigurasi Gravity Fill untuk Tab berada di posisi yang proposional
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(WisataDetailActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_review, null);
                final RatingBar ratingBar = (RatingBar) mView.findViewById(R.id.userRating);
                final EditText review = (EditText) mView.findViewById(R.id.userInput);
                Button mAdd = (Button) mView.findViewById(R.id.btnAdd);
                Button mCanc = (Button) mView.findViewById(R.id.btnCancel);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();
                mAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(ratingBar.getRating() > 0 && !review.getText().toString().isEmpty()){
                            rating = ratingBar.getRating();
                            komentar = review.getText().toString();
                            createReview();
                            dialog.dismiss();
                            Toast.makeText(WisataDetailActivity.this,"review ditambahkan", Toast.LENGTH_SHORT).show();
                        }
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

        mInterstitialAd = new InterstitialAd(this);

        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
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
                Toast.makeText(getApplicationContext(), "Ad is opened!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initFirebase(){
        FirebaseApp.initializeApp(getApplicationContext());
        mFireBaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFireBaseDatabase.getReference();
    }

    private void createReview() {
        Review review = new Review();
        review.setEmail(this.email);
        review.setKomentar(komentar);
        review.setNama(this.Nama);
        review.setRating(rating);
        review.setTanggal(this.tanggal);
        mDatabaseReference.child("review").child(id_provinsi+"").child(id_kota+"").child(id_tempat+"").child(username).setValue(review);
    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }
}
