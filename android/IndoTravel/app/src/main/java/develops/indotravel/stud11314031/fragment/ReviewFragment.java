package develops.indotravel.stud11314031.fragment;


import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import develops.indotravel.stud11314031.R;
import develops.indotravel.stud11314031.adapter.ReviewAdapter;
import develops.indotravel.stud11314031.model.Review;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewFragment extends Fragment {


    private RecyclerView recyclerView;
    private ReviewAdapter adapter;
    private FirebaseDatabase mFireBaseDatabase;
    private DatabaseReference mDatabaseReference;

    private List<Review> list_review = new ArrayList<Review>();

    int id_provinsi=1;
    int id_kota=1;
    int id_tempat=1;
    String Nama= "Aditya Pranata";
    String email="siregaraditya@gmail.com";
    String tanggal="16/05/2017";

    public String komentar;
    int rating;

    public ReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        id_provinsi  =  getActivity().getIntent().getIntExtra("id_provinsi",0);
        id_kota  =  getActivity().getIntent().getIntExtra("id_kota",0);
        id_tempat = getActivity().getIntent().getIntExtra("id_wisata",0);

        initFirebase();
        AddEventFirebaseListener();

        return view;
    }

    private void AddEventFirebaseListener() {
        mDatabaseReference.child("review").child(id_provinsi+"").child(id_kota+"").child(id_tempat+"")
                .addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(list_review.size()>0){
                        list_review.clear();
                    }
                    for(DataSnapshot postSnapShot:dataSnapshot.getChildren()){
                        Review review = postSnapShot.getValue(Review.class);
                        list_review.add(review);
                    }

                    adapter = new ReviewAdapter(getActivity(), list_review);
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.addItemDecoration(new ReviewFragment.GridSpacingItemDecoration(2, dpToPx(10), true));
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
        });
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


    private void initFirebase(){
        FirebaseApp.initializeApp(this.getActivity());
        mFireBaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFireBaseDatabase.getReference();
    }


}
