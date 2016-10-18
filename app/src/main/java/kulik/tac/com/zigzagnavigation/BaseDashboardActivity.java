package kulik.tac.com.zigzagnavigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

/**
 * Created by dima on 5/21/15.
 */
public class BaseDashboardActivity extends BaseNavigationDrawerActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tablet_main;
    }

    @Override
    protected BaseDrawerFlow getDrawerFlow() {
        return new TabletDrawerFlow();
    }

    @Override
    protected Fragment onCreateContentFragment() {
        return new MainFragment();
    }

    @Override
    protected void onInitToolBar(Toolbar toolBar) {
        super.onInitToolBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }
}
