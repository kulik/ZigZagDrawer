package kulik.tac.com.zigzagnavigation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * Created by kulik on 21.01.15.
 */
public interface IAction {
    public void call(FragmentActivity ctx, Fragment frg);
}
