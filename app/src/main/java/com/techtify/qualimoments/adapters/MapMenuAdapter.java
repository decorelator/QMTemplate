package com.techtify.qualimoments.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.techtify.qualimoments.R;
import com.techtify.qualimoments.model.MapMenuItem;

import java.util.ArrayList;


public class MapMenuAdapter extends BaseAdapter {

    private final ArrayList<MapMenuItem> items;
    private LayoutInflater layoutInflater;

    // 1
    public MapMenuAdapter(Context context, ArrayList<MapMenuItem> items) {
        this.items = items;
        layoutInflater = LayoutInflater.from(context);
    }

    // 2
    @Override
    public int getCount() {
        return items.size();
    }

    // 3
    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 4
    @Override
    public MapMenuItem getItem(int position) {
        return items.get(position);
    }

    // 5
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.map_menu_list_item, parent, false);
            holder = new ViewHolder();
            holder.title = convertView.findViewById(R.id.text);
            holder.icon = convertView.findViewById(R.id.icon);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        MapMenuItem item = getItem(position);

        holder.title.setText(item.getName());
        holder.icon.setImageResource(item.getResId());


        return convertView;
    }

    static class ViewHolder {
        TextView title;
        ImageView icon;
    }
}
