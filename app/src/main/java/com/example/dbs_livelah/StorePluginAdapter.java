package com.example.dbs_livelah;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class StorePluginAdapter extends BaseAdapter {
    private final Context mContext;
    private final Plugin[] plugins;

    public StorePluginAdapter(Context context, Plugin[] plugins) {
        this.mContext = context;
        this.plugins = plugins;
    }

    @Override
    public int getCount() {
        return plugins.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Plugin plugin = plugins[i];

        if (view == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            view = layoutInflater.inflate(R.layout.linearlayout_appstore, null);
        }
        // 3
        final ImageView imageView = (ImageView)view.findViewById(R.id.imageview_store_plugin);
        final TextView nameTextView = (TextView)view.findViewById(R.id.textview_store_plugin_name);
        final TextView descriptionTextView = (TextView)view.findViewById(R.id.textview_store_plugin_description);


        // 4
        Picasso.get().load(plugin.getImageURL()).into(imageView);
        nameTextView.setText(plugin.getName());
        descriptionTextView.setText(plugin.getDescription());

        return view;
    }
}
