package develops.indotravel.stud11314031.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import develops.indotravel.stud11314031.R;
import develops.indotravel.stud11314031.model.News;

/**
 * Created by Vranata on 11/05/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private Context mContext;
    private List<News> newsList;

    public class MyViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

        public TextView name,sumber;
        public ImageView thumbnail;

        News news;

        public MyViewHolder(View view){
            super(view);
            view.setOnClickListener(this);
            name = (TextView) view.findViewById(R.id.itemName);
            sumber = (TextView) view.findViewById(R.id.itemSumber);
            thumbnail = (ImageView) view.findViewById(R.id.itemImage);
        }

        public void setNews(News news){
            this.news = news;
        }

        @Override
        public void onClick(View view) {
            Uri uri = Uri.parse(news.getUri());
            Intent i = new Intent(Intent.ACTION_VIEW, uri);
            mContext.startActivity(i);
        }

    }

    public NewsAdapter(Context mContext, List<News> newsList) {
        this.mContext = mContext;
        this.newsList = newsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        News news = newsList.get(position);
        holder.name.setText(news.getName());
        holder.sumber.setText(news.getSumber());
        Glide.with(mContext).load(news.getUri()).into(holder.thumbnail);
        holder.setNews(news);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
}

