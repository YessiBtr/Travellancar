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

import develops.indotravel.stud11314031.TempatWisataActivity;
import develops.indotravel.stud11314031.model.WisataKota;
import develops.indotravel.stud11314031.R;

/**
 * Created by Vranata on 11/05/2017.
 */

public class WisataKotaAdapter extends RecyclerView.Adapter<WisataKotaAdapter.MyViewHolder> {

    private Context mContext;
    private List<WisataKota> wisataList;
    int provinsi_id = 0;

    public class MyViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        public TextView title, count;
        public ImageView thumbnail, overflow;

        WisataKota wisata;

        public MyViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        }

        public void setWisata(WisataKota wisata){
            this.wisata = wisata;
        }

        @Override
        public void onClick(View view) {
            Intent nextScreen = new Intent(mContext, TempatWisataActivity.class);
            nextScreen.putExtra("id_kota",wisata.getId());
            nextScreen.putExtra("id_provinsi",provinsi_id);
            mContext.startActivity(nextScreen);
        }

    }

    public int getProvinsi_id() {
        return provinsi_id;
    }

    public void setProvinsi_id(int provinsi_id) {
        this.provinsi_id = provinsi_id;
    }

    public WisataKotaAdapter(Context mContext, List<WisataKota> wisataList) {
        this.mContext = mContext;
        this.wisataList = wisataList;
    }

    @Override
    public WisataKotaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wisata_kota, parent, false);
        return new WisataKotaAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final WisataKotaAdapter.MyViewHolder holder, int position) {
        WisataKota wisata = wisataList.get(position);
        holder.title.setText(wisata.getKota());
        holder.count.setText(wisata.getJumlahTempatWisata() + " tempat wisata");
        Glide.with(mContext).load(wisata.getImage()).into(holder.thumbnail);
        holder.setWisata(wisata);
    }

    @Override
    public int getItemCount() {
        return wisataList.size();
    }
}
