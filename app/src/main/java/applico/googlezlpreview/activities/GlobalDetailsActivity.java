package applico.googlezlpreview.activities;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.astuetz.PagerSlidingTabStrip;

import applico.googlezlpreview.R;
import applico.googlezlpreview.adapters.GlobalDetailPagerAdapter;
import applico.googlezlpreview.adapters.GlobalPagerAdapter;
import applico.googlezlpreview.fragments.GlobalDetailFragment;
import applico.googlezlpreview.utils.GenericConstants;

public class GlobalDetailsActivity extends FragmentActivity implements GlobalDetailFragment.OnFragmentInteractionListener {

    private static final String LOG_TAG = GlobalDetailsActivity.class.getSimpleName();

    private static final String mTitle = "";
    private GlobalDetailPagerAdapter mGlobalDetailFragmentAdapter;
    private ViewPager mViewPager;
    private ImageView mBaseImage;

    private static final int TEXT_SIZE = 40;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Call this before setting the content in your view
        requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        messWithBars();

        setContentView(R.layout.activity_global_details);
        mBaseImage = (ImageView)findViewById(R.id.global_details_image);


        mGlobalDetailFragmentAdapter = new GlobalDetailPagerAdapter(getSupportFragmentManager(), GenericConstants.global_details_titles);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mGlobalDetailFragmentAdapter);

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
        getMenuInflater().inflate(R.menu.global_details, menu);
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
     * This class is intended to get the full bleed images working properly.
     */
    private void messWithBars()
    {
        Window w = getWindow();
        /**
         * Set Flags and Set Status Bar Color combination allows for full bleed into the status bar
         * Should be done in a layout so you don't have to write logic for SDK versions
         * //TODO move to layout
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            w.setStatusBarColor(getResources().getColor(android.R.color.transparent));
        }
        getActionBar().setTitle(mTitle);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbarinvisible));
    }

    /**
     * Dummy implementation.
     * @param uri
     */
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
