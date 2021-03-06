package applico.googlezlpreview.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;

import applico.googlezlpreview.R;
import applico.googlezlpreview.adapters.GlobalDetailPagerAdapter;
import applico.googlezlpreview.fragments.GlobalDetailFragment;
import applico.googlezlpreview.views.FabView;

/**
 * The intention of this activity is to show off, shared elements as well as a circular reveal.
 * What interesting is that the way L is constitued now, transforming is not supported so I created my own transform and didn't
 * move the element to simulate the a shared like transition.  We also are showing off a tintable animation in this class that
 * tints the image from a sample of the lightest vibrant color from the Palette class.
 * @author Matt Powers
 */
public class GlobalDetailsActivity extends FragmentActivity implements GlobalDetailFragment.OnFragmentInteractionListener {

    private static final String LOG_TAG = GlobalDetailsActivity.class.getSimpleName();

    private GlobalDetailPagerAdapter mGlobalDetailFragmentAdapter;
    private ViewPager mViewPager;
    private ImageView mBaseIV;
    private TextView mTitleTV;
    private TextView mTitleRankTV;

    private FabView mFabView;

    private String mTitle;
    private String mTitleBlank = "";
    private String mSummary;

    public static final String TITLE_KEY = "title_key";
    public static final String RANK_KEY = "rank_key";
    public static final String SUMMARY_KEY = "summary_key";
    public static final String RESOURCE_KEY = "resource_key";

    //Key for the shared elements between activities
    public static final String SHARED_IMAGE = "image";
    public static final String SHARED_FAB_VIEW = "fab_view";


    private static final int TEXT_SIZE = 40;
    private static final int TINT_START = -100;
    private static final int TINT_END = -50;
    private static final int TINT_ANIM_TIME = 2000;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Call this before setting the content in your view
        requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        messWithBars();
        setContentView(R.layout.activity_global_details);

        //Get the bundle to process the shared elements on
        Bundle bundle = getIntent().getExtras();

        //Setup the shared resources
        mBaseIV = (ImageView) findViewById(R.id.global_details_image_details);
        mBaseIV.setTransitionName(SHARED_IMAGE);
        mFabView = (FabView) findViewById(R.id.fab_view_details);
        mFabView.setTransitionName(SHARED_FAB_VIEW);

        mTitleTV = (TextView) findViewById(R.id.base_caption_details);
        mTitleRankTV = (TextView) findViewById(R.id.base_caption_num_details);

        //TODO pull view pager titles from string resource file
        mGlobalDetailFragmentAdapter = new GlobalDetailPagerAdapter(getSupportFragmentManager(),
                getResources().getStringArray(R.array.global_details_titles));
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mGlobalDetailFragmentAdapter);

        //The tabs need to bind to the pager
        Resources resource = getResources();
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(mViewPager);
        tabs.setTextColor(resource.getColor(R.color.appBarTextColor));
        tabs.setTextSize(TEXT_SIZE);
        tabs.setDividerColor(resource.getColor(R.color.appBarColor));

        loadItems(bundle);

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
        switch(id)
        {
            case R.id.action_settings:
                return true;
            case android.R.id.home:
                finishAfterTransition();

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
        getActionBar().setTitle(mTitleBlank);
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

    @Override
    public void onBackPressed() {
        //This will reanimate to the previous activity
        finishAfterTransition();
    }


    /**
     * This takes care of setting up all the elements, including any animations
     * @param bundle
     */
    private void loadItems(Bundle bundle)
    {
        mBaseIV.setImageDrawable(getResources().getDrawable(bundle.getInt(RESOURCE_KEY)));
        mTitleTV.setText(bundle.getString(TITLE_KEY));
        mTitleRankTV.setText(bundle.getString(RANK_KEY));
        final BitmapDrawable d = (BitmapDrawable) mBaseIV.getDrawable();

        //Set the tint and animate
        Palette.generateAsync(d.getBitmap(), new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette p) {
                // Do something with colors...
                //Get the light vibrant color in the image and tint it dark
                final int color = p.getLightVibrantColor(getResources().getColor(R.color.cardview_light_background));

                ValueAnimator anim = ValueAnimator.ofInt(TINT_START,TINT_END);
                anim.setDuration(TINT_ANIM_TIME);
                anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        int percent = (Integer)valueAnimator.getAnimatedValue();
                        int val = getShadedColor(color, percent);
                        d.setTint(val);
                        d.setTintMode(PorterDuff.Mode.LIGHTEN);
                    }
                });
                anim.start();
            }
        });


        //Fade in animation for the text
        Animation fadeIn = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        mTitleTV.startAnimation(fadeIn);
        mTitleRankTV.startAnimation(fadeIn);

        //TODO - I should be able to use the changeTransform style, but it doesn't appear to be working
        Animation scaleFab = AnimationUtils.loadAnimation(this,R.anim.scale_down);
        mFabView.startAnimation(scaleFab);

        /**
         * Alternatively I could use the circular reveal effect and not scale using the animation class, this code is overkill
         * I would probably just use the Animation class and a scale animation from my animation resources if given the choice
         */

    }

    /**
     * RGB value for shaded color
     * @param color
     * @param percent
     * @return
     */
    private int getShadedColor(int color, int percent)
    {
        int R = Color.red(color);
        int G = Color.green(color);
        int B = Color.blue(color);

        R = (R * (100 + percent)) / 100;
        G = (G * (100 + percent)) / 100;
        B = (B * (100 + percent)) / 100;

        R = (R<255)?R:255;
        G = (G<255)?G:255;
        B = (B<255)?B:255;

        return Color.rgb(R,G,B);

    }
}
