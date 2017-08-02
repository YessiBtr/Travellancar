package develops.indotravel.stud11314031.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import develops.indotravel.stud11314031.R;
import develops.indotravel.stud11314031.model.Review;

/**
 * Created by Vranata on 11/05/2017.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Review> reviewList;

    public class MyViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

        public TextView name,date,deskripsi;
        public RatingBar ratingBar;
        public ImageView thumbnail;

        Review review;

        public MyViewHolder(View view){
            super(view);
            view.setOnClickListener(this);
            name = (TextView) view.findViewById(R.id.itemName);
            date = (TextView) view.findViewById(R.id.itemDate);
            deskripsi = (TextView) view.findViewById(R.id.itemDeskripsi);
            thumbnail = (ImageView) view.findViewById(R.id.itemImage);
            ratingBar = (RatingBar) view.findViewById(R.id.itemRating);
        }

        public void setNews(Review review){
            this.review =review;
        }

        @Override
        public void onClick(View view) {
//            Uri uri = Uri.parse(news.getUri());
//            Intent i = new Intent(Intent.ACTION_VIEW, uri);
//            mContext.startActivity(i);
        }

    }

    public ReviewAdapter(Context mContext, List<Review> reviewList) {
        this.mContext = mContext;
        this.reviewList = reviewList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Review review = reviewList.get(position);
        holder.name.setText(review.getNama());
        holder.ratingBar.setRating(review.getRating());
        holder.deskripsi.setText(review.getKomentar());
        holder.date.setText(review.getTanggal());
        Glide.with(mContext).load(R.drawable.album10).into(holder.thumbnail);
        holder.setNews(review);
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }
}

