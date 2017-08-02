package develops.indotravel.stud11314031.adapter;

/**
 * Created by Vranata on 11/05/2017.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import develops.indotravel.stud11314031.fragment.ArticleFragment;
import develops.indotravel.stud11314031.fragment.WisataFragment;


public class TabFragmentPagerAdapter extends  FragmentPagerAdapter {

    //nama tab nya
    String[] title = new String[]{
            "Wisata", "Article"
    };

    public TabFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    //method ini yang akan memanipulasi penampilan Fragment dilayar
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new WisataFragment();
                break;
            case 1:
                fragment = new ArticleFragment();
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
