package applico.googlezlpreview.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import applico.googlezlpreview.fragments.GlobalDetailFragment;
import applico.googlezlpreview.fragments.GlobalFragment;

/**
 * This is for managing the swipes between the different global
 * categories
 * @author Matt Powers
 * TODO - We need to actually handle more than one category, again this is a demo
 */
public class GlobalDetailPagerAdapter extends FragmentStatePagerAdapter {

    private static String LOG_TAG = GlobalDetailPagerAdapter.class.getSimpleName();

    private int mItems;
    private String[] mTitles;

    public GlobalDetailPagerAdapter(android.support.v4.app.FragmentManager fm, String[] titles) {
        super(fm);
        mItems = titles.length;
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new GlobalDetailFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;


    }

    @Override
    public int getCount() {
        return mItems;

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
