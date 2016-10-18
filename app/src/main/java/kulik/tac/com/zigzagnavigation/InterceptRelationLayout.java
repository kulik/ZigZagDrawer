
package kulik.tac.com.zigzagnavigation;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by tac on 28.07.15.
 */
public class InterceptRelationLayout extends RelativeLayout {

    private OnTouchListener mOnTouchListener;

    public InterceptRelationLayout(Context context) {
        super(context);
    }

    public InterceptRelationLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InterceptRelationLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public InterceptRelationLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(mOnTouchListener!=null){
            mOnTouchListener.onTouch(this, ev);
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public void setOnTouchListener(OnTouchListener onTouchListener) {
        mOnTouchListener = onTouchListener;
    }
}
