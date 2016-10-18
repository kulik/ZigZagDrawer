package kulik.tac.com.zigzagnavigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;


/**
 * Created by mda on 1/16/15.
 */
public abstract class ContentActivity<T extends Fragment> extends AppCompatActivity {

    protected Toolbar mToolBar;

    protected T mContentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.inject(this);
        if (savedInstanceState == null) {
            mContentFragment = onCreateContentFragment();
            if(mContentFragment!=null){
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, mContentFragment)
                        .commit();
            }
            afterSetUpContentFragment();
        } else {
            mContentFragment = (T) getSupportFragmentManager().findFragmentById(R.id.container);
        }
        mToolBar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        if(mToolBar != null) {
            mToolBar.setTitle(getTitle());
            setSupportActionBar(mToolBar);
            onInitToolBar(mToolBar);
        }
    }

    protected void afterSetUpContentFragment() {}

    protected void onInitToolBar(Toolbar toolBar) {
        // If ancestor what customize toolbar they need override this method.
    }

    protected int getLayoutId() {
        return R.layout.activity_tool_container;
    }

    protected abstract T onCreateContentFragment();


    public Fragment getContentFragment() {
        return mContentFragment;
    }

    public int getAnimationOut(){
        return R.anim.slide_out_right;
    }

    public int getAnimationIn(){
        return R.anim.slide_in_left;
    }
}
