package kulik.tac.com.zigzagnavigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import butterknife.InjectView;

public abstract class BaseNavigationDrawerActivity extends ContentActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks, CurrentFragmentCallBack {
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    protected NavigationDrawerFragment mNavigationDrawerFragment;

    private BaseDrawerFlow mDrawerController;

    @InjectView(R.id.zoom_view)
    protected RelativeLayout mZoomView;
    @InjectView(R.id.rotation_view)
    protected RotationView mDividerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);
        mDrawerController = getDrawerFlow();
        mDrawerController.setActivity(this);

        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        mDividerView.setContentView(mZoomView);
        mDividerView.setDrawerView(mNavigationDrawerFragment.getView(), mNavigationDrawerFragment.getDrawerListView());

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        mDrawerController.doAction(position);
    }

    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen()) {
            mNavigationDrawerFragment.closeDrawer();
        }
    }

    @Override
    public void setCurrentFragment(Fragment fragment) {
        mContentFragment = fragment;
    }

    @Override
    public void onScroll(float percents) {
        mDividerView.onScroll(percents);
    }

    protected abstract BaseDrawerFlow getDrawerFlow();
}

