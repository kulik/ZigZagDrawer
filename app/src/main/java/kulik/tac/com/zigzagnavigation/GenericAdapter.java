package kulik.tac.com.zigzagnavigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import java.util.Collection;
import java.util.List;

/**
 * User: mda
 * Date: 10/3/13
 * Time: 10:09 AM
 */
public abstract class GenericAdapter<T> extends ArrayAdapter<T> {

    protected LayoutInflater mInflater;

    private boolean mNotifyOnChange;
    private final Object mLock = new Object();

    public GenericAdapter(Context context, List<T> objects) {
        super(context, 0, objects);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * Adds the specified Collection at the end of the array.
     *
     * @param collection The Collection to add at the end of the array.
     */
    public void addAll(Collection<? extends T> collection) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            super.addAll(collection);
        } else {
            synchronized (mLock) {
                boolean changed = false;
                if (mNotifyOnChange) {
                    setNotifyOnChange(false);
                    changed = true;
                }

                for (T item : collection) {
                    add(item);
                }

                if (changed) setNotifyOnChange(true);
            }

            if (mNotifyOnChange) notifyDataSetChanged();
        }
    }

    /**
     * Adds the specified Collection at the end of the array.
     *
     * @param collection The Collection to add at the end of the array.
     */
    public void replaceAll(Collection<? extends T> collection) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            synchronized (mLock) {
                boolean changed = false;
                if (mNotifyOnChange) {
                    setNotifyOnChange(false);
                    changed = true;
                }
                super.clear();
                super.addAll(collection);
                if (changed) setNotifyOnChange(true);
            }
        } else {
            synchronized (mLock) {
                boolean changed = false;
                if (mNotifyOnChange) {
                    setNotifyOnChange(false);
                    changed = true;
                }

                super.clear();
                for (T item : collection) {
                    add(item);
                }

                if (changed) setNotifyOnChange(true);
            }

            if (mNotifyOnChange) notifyDataSetChanged();
        }
    }


    @Override
    public void setNotifyOnChange(boolean notifyOnChange) {
        synchronized (mLock) {
            super.setNotifyOnChange(notifyOnChange);
            mNotifyOnChange = notifyOnChange;
        }
    }
}
