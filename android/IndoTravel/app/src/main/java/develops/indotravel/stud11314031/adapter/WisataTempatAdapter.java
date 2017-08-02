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

import develops.indotravel.stud11314031.R;
import develops.indotravel.stud11314031.WisataDetailActivity;
import develops.indotravel.stud11314031.model.WisataTempat;

/**
 * Created by Vranata on 11/05/2017.
 */

public class WisataTempatAdapter extends RecyclerView.Adapter<WisataTempatAdapter.MyViewHolder> {

    private Context mContext;
    private List<WisataTempat> wisataList;

    int id_provinsi=0;
    int id_kota=0;

    public class MyViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        public TextView title, keterangan;
        public ImageView thumbnail, overflow;

        WisataTempat wisata;

        public MyViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            title = (TextView) view.findViewById(R.id.itemName);
            keterangan = (TextView) view.findViewById(R.id.itemDeskripsi);
            thumbnail = (ImageView) view.findViewById(R.id.itemIcon);
        }

        public void setWisata(WisataTempat wisata){
            this.wisata = wisata;
        }

        @Override
        public void onClick(View view) {
            Intent nextScreen = new Intent(mContext, WisataDetailActivity.class);
            nextScreen.putExtra("id_provinsi",id_provinsi);
            nextScreen.putExtra("id_kota",getId_kota());
            nextScreen.putExtra("id_wisata",wisata.getId());
            nextScreen.putExtra("keterangan",wisata.getKeterangan());
            nextScreen.putExtra("nama_tempat",wisata.getNama_tempat());
            nextScreen.putExtra("sumber_gambar",wisata.getSumber_gambar());
            mContext.startActivity(nextScreen);
        }

    }

    public int getId_provinsi() {
        return id_provinsi;
    }

    public void setId_provinsi(int id_provinsi) {
        this.id_provinsi = id_provinsi;
    }

    public int getId_kota() {
        return id_kota;
    }

    public void setId_kota(int id_kota) {
        this.id_kota = id_kota;
    }

    public WisataTempatAdapter(Context mContext, List<WisataTempat> wisataList) {
        this.mContext = mContext;
        this.wisataList = wisataList;
    }

    @Override
    public WisataTempatAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tempat_wisata, parent, false);
        return new WisataTempatAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final WisataTempatAdapter.MyViewHolder holder, int position) {
        WisataTempat wisata = wisataList.get(position);
        holder.title.setText(wisata.getNama_tempat());
        holder.keterangan.setText(wisata.getKeterangan());
        Glide.with(mContext).load(wisata.getSumber_gambar()).into(holder.thumbnail);
        holder.setWisata(wisata);
    }


    @Override
    public int getItemCount() {
        return wisataList.size();
    }
}
