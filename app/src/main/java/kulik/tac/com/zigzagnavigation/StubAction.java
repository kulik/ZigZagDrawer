package kulik.tac.com.zigzagnavigation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

/**
 * Created by kulik on 19.10.16.
 */
public class StubAction implements IAction {
    @Override
    public void call(FragmentActivity ctx, Fragment frg) {
        Toast.makeText(ctx, "SomeOption selected", Toast.LENGTH_SHORT).show();
    }
}
