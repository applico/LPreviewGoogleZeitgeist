package applico.googlezlpreview.activities;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import applico.googlezlpreview.R;

public class GlobalDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Call this before setting the content in your view
        requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.fragment_global_detail);
        messWithBars();


        /*if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }*/
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
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_glogal_details_deleteme, container, false);
            return rootView;
        }
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
        getActionBar().setTitle("");
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbarinvisible));
    }

}
