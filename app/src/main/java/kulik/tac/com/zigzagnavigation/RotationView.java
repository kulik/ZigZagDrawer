package kulik.tac.com.zigzagnavigation;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by tac on 22.07.15.
 */
public class RotationView extends View {

    private int mShadowSize;

    private Matrix mMatrix;
    private Paint paint;

    private Drawable mShadow;

    private View mContentView;
    private View mDrawerView;

    private float margin;
    private int w;
    private float mMaxContentScale;
    private float mTranslationScale;
    private float dividerContentWidth = 1;

    public RotationView(Context context) {
        super(context);
        init(context, null);
    }

    public RotationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RotationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attributeSet) {
        mMatrix = new Matrix();

        if (attributeSet != null) {
            TypedArray attr = context.getTheme().obtainStyledAttributes(
                    attributeSet,
                    R.styleable.SideMenu,
                    0, 0);

            mShadow = attr.getDrawable(R.styleable.SideMenu_shadow);
            mShadowSize = attr.getDimensionPixelSize(R.styleable.SideMenu_shadow_size, context.getResources().getDimensionPixelSize(R.dimen.shadow_size));
            mMaxContentScale = 1 - attr.getFloat(R.styleable.SideMenu_maxContentScale, 0.85f);
            mTranslationScale = 1 + attr.getFloat(R.styleable.SideMenu_translationScale, 0f);
            attr.recycle();
        }

        if (mShadow == null) {
            mShadow = context.getResources().getDrawable(R.drawable.side_menu_shadow);
        }

        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (!isViewsSet() || w == 0) {
            return;
        }

        int h = getHeight();
        canvas.save();
        mMatrix.setPolyToPoly(new float[]{0, 0, dividerContentWidth, 0, dividerContentWidth, h, 0, h}, 0, new float[]{0, 0, w, margin, w, h - margin, 0, h}, 0, 4);
        canvas.clipRect(0, 0, w, h);
        canvas.concat(mMatrix);
        mDrawerView.draw(canvas);
        canvas.restore();
        mShadow.setBounds(0, 0, mShadowSize, h);
        mShadow.draw(canvas);
    }

    private boolean isViewsSet() {
        return mContentView != null && mDrawerView != null;
    }

    public void onScroll(float percents) {

        if (!isViewsSet() && w == 0) {
            return;
        }

        float scale = 1 - (percents * mMaxContentScale);
        int drawerPixelWidth = getResources().getDimensionPixelSize(R.dimen.navigation_drawer_width);

        float translationDrawer = drawerPixelWidth * percents;
        float translationContent = translationDrawer * percents * mTranslationScale;

        mContentView.setTranslationX(translationContent);
        mContentView.setScaleX(scale);
        mContentView.setScaleY(scale);

        float horizontalMargin = mContentView.getWidth() * (1 - scale) / 2;

        if (translationContent + horizontalMargin > translationDrawer) {


            setVisibility(View.VISIBLE);

            int viewWidth = Math.round(translationContent + horizontalMargin - translationDrawer);
            float verticalMargin = Math.round(mContentView.getHeight() * (1 - scale) / 2);

            rotate(viewWidth, verticalMargin);
            setTranslationX(translationDrawer);
        } else {
            setVisibility(View.GONE);
        }
    }

    public void setDrawerView(View mDrawerView, ListView listView) {
        this.mDrawerView = mDrawerView;
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                invalidate();
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                invalidate();
            }
        });
    }

    public void setContentView(View mContentView) {
        this.mContentView = mContentView;
    }

    /**
     * Rotates view according to params
     *
     * @param width  - view width
     * @param margin - main content margin
     */

    public void rotate(int width, float margin) {
        this.margin = margin;
        w = width;
        invalidate();
    }
}
