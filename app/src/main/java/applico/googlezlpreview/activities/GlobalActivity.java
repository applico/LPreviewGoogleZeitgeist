package applico.googlezlpreview.activities;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;

import applico.googlezlpreview.R;
import applico.googlezlpreview.adapters.GlobalPagerAdapter;
import applico.googlezlpreview.fragments.GlobalFragment;
import applico.googlezlpreview.utils.GenericConstants;

/**
 * The intention of this class is to show the global activities to the user for the past year
 */

public class GlobalActivity extends BaseDrawerFragmentActivity implements GlobalFragment.OnGlobalFragmentInteractionListener {

    private static String LOG_TAG = GlobalActivity.class.getSimpleName();

    private GlobalPagerAdapter mGlobalFragmentAdapter;
    private ViewPager mViewPager;

    private static final int TEXT_SIZE = 40;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_activity);

        /**
         * Sweet the view pager is in the support library, makes total sense.  Please force me to use
         * more of the support packages.
        */

        mGlobalFragmentAdapter = new GlobalPagerAdapter(getSupportFragmentManager(), GenericConstants.global_titles);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mGlobalFragmentAdapter);

        //The tabs need to bind to the pager
        Resources resource = getResources();
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(mViewPager);
        tabs.setTextColor(resource.getColor(R.color.appBarTextColor));
        tabs.setTextSize(TEXT_SIZE);
        tabs.setDividerColor(resource.getColor(R.color.appBarColor));


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.global_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Communication back to the activity, I will probably use this for activity -> fragment-> activity -> fragment transactions
     * @param uri
     */

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
