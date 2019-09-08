package com.example.dbs_livelah;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;



public class PluginAdapter extends BaseAdapter {

    private final Context mContext;
    private final Plugin[] plugins;

    public PluginAdapter(Context context, Plugin[] plugins){
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

        // 2
        if (view == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            view = layoutInflater.inflate(R.layout.linearlayout_plugin, null);
        }

        // 3
        final ImageView imageView = (ImageView)view.findViewById(R.id.imageview_plugin);
        final TextView nameTextView = (TextView)view.findViewById(R.id.textview_plugin_name);

        // 4
        Picasso.get().load(plugin.getImageURL()).into(imageView);
        nameTextView.setText(plugin.getName());

        return view;
    }
}