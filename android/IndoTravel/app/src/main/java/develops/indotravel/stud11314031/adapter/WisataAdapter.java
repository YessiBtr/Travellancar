package develops.indotravel.stud11314031.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import java.util.List;

import develops.indotravel.stud11314031.WisataKotaActivity;
import develops.indotravel.stud11314031.model.Wisata;
import develops.indotravel.stud11314031.R;

/**
 * Created by Vranata on 11/05/2017.
 */

public class WisataAdapter extends RecyclerView.Adapter<WisataAdapter.MyViewHolder> {

    private Context mContext;
    private List<Wisata> wisataList;

    public class MyViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        public TextView title, count;
        public ImageView thumbnail, overflow;

        Wisata wisata;

        public MyViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        }

        public void setWisata(Wisata wisata){
            this.wisata = wisata;
        }

        @Override
        public void onClick(View view) {
//            Toast.makeText(mContext, wisata.getSumber_gambar(),Toast.LENGTH_LONG).show();
            Intent nextScreen = new Intent(mContext, WisataKotaActivity.class);
            nextScreen.putExtra("id",wisata.getId());
            mContext.startActivity(nextScreen);
        }
    }

    public WisataAdapter(Context mContext, List<Wisata> wisataList) {
        this.mContext = mContext;
        this.wisataList = wisataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wisata, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Wisata wisata = wisataList.get(position);
        holder.title.setText(wisata.getNama_provinsi());
        holder.count.setText(wisata.getJumlah_kota() + " kota wisata");
        Glide.with(mContext).load(wisata.getSumber_gambar()).into(holder.thumbnail);
        holder.setWisata(wisata);
    }

    @Override
    public int getItemCount() {
        return wisataList.size();
    }
}

