package develops.indotravel.stud11314031.fragment;


import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import develops.indotravel.stud11314031.R;
import develops.indotravel.stud11314031.adapter.NewsAdapter;
import develops.indotravel.stud11314031.api.NewsAPI;
import develops.indotravel.stud11314031.api.ResponseAPI;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import develops.indotravel.stud11314031.api.RestClient;
import develops.indotravel.stud11314031.model.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleFragment extends Fragment {

    private RecyclerView recyclerView;

    NewsAPI  newsAPI;
    List<News> newsList;
    private NewsAdapter adapter;
    public int jumlahDatas=0;

    public ArticleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        newsList = new ArrayList<News>();

        View view = inflater.inflate(R.layout.fragment_article, container, false);
        recyclerView = (RecyclerView)  view.findViewById(R.id.recycler_view);


        Callback<ResponseAPI> hasiltandingbola = new Callback<ResponseAPI>() {
            @Override
            public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                if (response.isSuccessful()) {
                    //menampung isi dari List<ModelHasilBola> dariResponseAPI
                    newsList = response.body().getHasil();

                    adapter = new NewsAdapter(getActivity(), newsList);
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.addItemDecoration(new ArticleFragment.GridSpacingItemDecoration(2, dpToPx(10), true));
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                    int jumlahData = response.body().getHasil().size();
                    if(jumlahData>0){
                        jumlahDatas = jumlahData;
                    }else{
                        Toast.makeText(getContext(), "DATA SEDANG TIDAK TERSEDIA", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Log.e("onResponse failure", "Code: " + response.code() + " , Message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseAPI> call, Throwable t)
            {
                Log.e("Akses ke server gagal", "Code: " + t.getMessage() + " , Message: " + t.getCause());
                // TODO Auto-generated method stub
                Toast.makeText(getContext(), "AKSES KE SERVER GAGAL "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        };

        //consuming web service here
        newsAPI = RestClient.get();
        Call<ResponseAPI> callHasilBola = newsAPI.getHasilBerita();
        callHasilBola.enqueue(hasiltandingbola);
        newsAPI.getHasilBerita();

        return view;
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

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
