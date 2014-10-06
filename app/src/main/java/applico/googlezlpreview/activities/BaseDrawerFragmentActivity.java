package applico.googlezlpreview.activities;

import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;

import applico.googlezlpreview.NavigationDrawerFragment;
import applico.googlezlpreview.R;
import applico.googlezlpreview.fragments.DrawerFragment;

/**
 * Base Fragment Activity using the Nav Drawer
 */
public class BaseDrawerFragmentActivity extends FragmentActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks, DrawerFragment.OnDrawerFragmentInteractionListener {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private static final String LOG_TAG = BaseDrawerActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_drawer);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {

        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, DrawerFragment.newInstance(position + 1))
                .commit();
    }

    @Override
    public void onDrawerFragmentInteraction(Uri uri) {

    }
}
