package kulik.tac.com.zigzagnavigation;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mda on 1/30/15.
 */
public class DrawerAdapter extends GenericAdapter<String> {

    protected Context mCtx;

    public DrawerAdapter(Context context, List<String> objects) {
        super(context, objects);//mCtx.getResources().getStringArray(R.array.drawer_titles)
        mCtx = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = initHolder();
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.mTitle.setText(getItem(position));
        return convertView;
    }

    private View initHolder() {
        ViewHolder viewHolder = new ViewHolder();
        View view = mInflater.inflate(R.layout.item_drawer, null);
        viewHolder.mTitle = (TextView) view.findViewById(R.id.title);
        view.setTag(viewHolder);
        return view;
    }

    static class ViewHolder {
        TextView mTitle;

    }
}