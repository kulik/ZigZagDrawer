package kulik.tac.com.zigzagnavigation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import java.util.HashMap;

import butterknife.ButterKnife;
import kulik.tac.com.zigzagnavigation.CurrentFragmentCallBack;
import kulik.tac.com.zigzagnavigation.IAction;

/**
 * Created by kulik on 26.01.15.
 */
public abstract class BaseDrawerFlow implements CurrentFragmentCallBack {

    @Override
    public void setCurrentFragment(Fragment fragment) {
        mFrg = fragment;
    }

    private HashMap<Integer, IAction> mActionsMap = new HashMap<Integer, IAction>();

    {
        onActionMapInit(mActionsMap);
    }

    protected abstract void onActionMapInit(HashMap<Integer, IAction> actionsMap);

    private FragmentActivity mActivity;
    private Fragment mFrg;

    public void setActivity(FragmentActivity activity) {
        mActivity = activity;
    }

    public void init(View view, Fragment fragment) {
        mFrg = fragment;
        ButterKnife.inject(this, view);
    }

    public Fragment getCurrentFragment() {
        return mFrg;
    }

    public void doAction(int pos) {
        mActionsMap.get(pos).call(mActivity, mFrg);
    }
}

