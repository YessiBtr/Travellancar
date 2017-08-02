package develops.indotravel.stud11314031;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.List;

import develops.indotravel.stud11314031.adapter.TabFragmentPagerAdapter;
import develops.indotravel.stud11314031.adapter.WisataKotaAdapter;
import develops.indotravel.stud11314031.api.ResponseWisataAPI;
import develops.indotravel.stud11314031.api.RestWisataClient;
import develops.indotravel.stud11314031.api.WisataAPI;
import develops.indotravel.stud11314031.model.WisataKota;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WisataKotaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WisataKotaAdapter adapter;
    private List<WisataKota> wisataList;

    WisataAPI wisataAPI;
    public int jumlahDatas=0;
    int provinsi_id  = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wisata_kota);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        wisataList = new ArrayList<WisataKota>();
        provinsi_id = getIntent().getIntExtra("id",0);

        Callback<ResponseWisataAPI> hasilKota = new Callback<ResponseWisataAPI>() {
            @Override
            public void onResponse(Call<ResponseWisataAPI> call, Response<ResponseWisataAPI> response) {
                System.out.println(call.request().url().toString());
                if (response.isSuccessful()) {
                    //menampung isi dari List<ModelHasilBola> dariResponseAPI
                    wisataList = response.body().getKota();
                    adapter = new WisataKotaAdapter(WisataKotaActivity.this, wisataList);
                    adapter.setProvinsi_id(provinsi_id);
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(WisataKotaActivity.this, 1);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.addItemDecoration(new WisataKotaActivity.GridSpacingItemDecoration(2, dpToPx(10), true));
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                    int jumlahData = response.body().getKota().size();
                    if(jumlahData>0){
                        jumlahDatas = jumlahData;
                    }else{
//                        Toast.makeText(get, "DATA SEDANG TIDAK TERSEDIA", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Log.e("onResponse failure", "Code: " + response.code() + " , Message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseWisataAPI> call, Throwable t)
            {
                Log.e("Akses ke server gagal", "Code: " + t.getMessage() + " , Message: " + t.getCause());
            }
        };

        wisataAPI = RestWisataClient.get();
        Call<ResponseWisataAPI> callHasilBola = wisataAPI.getKota(provinsi_id+"");
        callHasilBola.enqueue(hasilKota);
        wisataAPI.getProvinsi();

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

    public static class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

        private Toolbar toolbar;
        private ViewPager pager;
        private TabLayout tabs;
        private FirebaseAnalytics firebaseAnalytics;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

            pager = (ViewPager) findViewById(R.id.pager);
            tabs = (TabLayout) findViewById(R.id.tabs);

            //set object adapter kedalam ViewPager
            pager.setAdapter(new TabFragmentPagerAdapter(getSupportFragmentManager()));

            //Manimpilasi sedikit untuk set TextColor pada Tab
            tabs.setTabTextColors(getResources().getColor(R.color.colorPrimaryDark),
                    getResources().getColor(android.R.color.white));

            //set tab ke ViewPager
            tabs.setupWithViewPager(pager);

            //konfigurasi Gravity Fill untuk Tab berada di posisi yang proposional
            tabs.setTabGravity(TabLayout.GRAVITY_FILL);

            firebaseAnalytics = FirebaseAnalytics.getInstance(this);

            //Sets whether analytics collection is enabled for this app on this device.
            firebaseAnalytics.setAnalyticsCollectionEnabled(true);

            //Sets the minimum engagement time required before starting a session. The default value is 10000 (10 seconds). Let's make it 20 seconds just for the fun
            firebaseAnalytics.setMinimumSessionDuration(20000);

            //Sets the duration of inactivity that terminates the current session. The default value is 1800000 (30 minutes).
            firebaseAnalytics.setSessionTimeoutDuration(500);
        }

        @Override
        public void onBackPressed() {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.

    //        Menu menu = (Menu) R.menu.main;
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

        @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            // Handle navigation view item clicks here.
            int id = item.getItemId();

            if (id == R.id.nav_login) {
                System.out.println("login");
                Intent login = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(login);
                System.out.println("login");
                // Handle the camera action
                // Handle the camera action
            } else if (id == R.id.nav_about) {
                Intent login = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(login);
            }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
    }
}
