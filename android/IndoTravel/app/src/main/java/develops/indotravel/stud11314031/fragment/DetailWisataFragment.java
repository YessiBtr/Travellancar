package develops.indotravel.stud11314031.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import develops.indotravel.stud11314031.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailWisataFragment extends Fragment {


    public DetailWisataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {// Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_detail_wisata, container, false);
        String keterangan = getActivity().getIntent().getExtras().getString("keterangan");
        TextView ket = (TextView) view.findViewById(R.id.detailWisata);
        ket.setText(keterangan);
        return view;
    }

}
