package com.techtify.qualimoments.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.techtify.qualimoments.R;
import com.techtify.qualimoments.model.QMenuItem;

public class MenuAdapter extends ArrayAdapter<QMenuItem> {
    private final LayoutInflater layoutInflater;


    public MenuAdapter(@NonNull Context context) {
        super(context, R.layout.menu_list_item);
        layoutInflater = LayoutInflater.from(context);

        final String[] values = context.getResources().getStringArray(R.array.menuItems);
        for (int i = 0; i < values.length; i++) {
            add(new QMenuItem(R.drawable.ic_menu_camera, values[i], i));
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.menu_list_item, parent, false);
            holder = new ViewHolder();
            holder.title = convertView.findViewById(R.id.text);
            holder.icon = convertView.findViewById(R.id.icon);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        QMenuItem qMenuItem = getItem(position);

        if (qMenuItem != null) {
            holder.title.setText(qMenuItem.getName());
            holder.icon.setImageResource(qMenuItem.getDrawable());
        }


        return convertView;
    }

    static class ViewHolder {
        TextView title;
        ImageView icon;
    }
}
