package develops.indotravel.stud11314031.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import develops.indotravel.stud11314031.fragment.DetailWisataFragment;
import develops.indotravel.stud11314031.fragment.ReviewFragment;

/**
 * Created by Vranata on 15/05/2017.
 */

public class TabFragmentPagerDetailAdapter extends FragmentPagerAdapter {

    //nama tab nya
    String[] title = new String[]{
            "Detail", "Review"
    };

    public TabFragmentPagerDetailAdapter(FragmentManager fm) {
        super(fm);
    }

    //method ini yang akan memanipulasi penampilan Fragment dilayar
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new DetailWisataFragment();
                break;
            case 1:
                fragment = new ReviewFragment();
                break;
            default:
                fragment = null;
                break;
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public int getCount() {
        return title.length;
    }
}
