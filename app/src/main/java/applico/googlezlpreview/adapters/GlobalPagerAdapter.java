package applico.googlezlpreview.adapters;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import applico.googlezlpreview.fragments.GlobalFragment;

/**
 *
 * This is for managing the swipes between the different global
 * categories
 * TODO - We need to actually handle more than one category, again this is a demo
 * @author Matt Powers
 */
public class GlobalPagerAdapter extends FragmentStatePagerAdapter {

    private static String LOG_TAG = GlobalPagerAdapter.class.getSimpleName();

    private int mItems;
    private String[] mTitles;

    public GlobalPagerAdapter(android.support.v4.app.FragmentManager fm, String[] titles) {
        super(fm);
        mItems = titles.length;
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new GlobalFragment();
        Bundle args = new Bundle();
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
