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
import com.techtify.qualimoments.model.ProfileModel;

public class ProfileAdapter extends ArrayAdapter<ProfileModel> {
    private final LayoutInflater layoutInflater;


    public ProfileAdapter(@NonNull Context context) {
        super(context, R.layout.menu_list_item);
        layoutInflater = LayoutInflater.from(context);


        for (int i = 0; i < 10; i++) {
            add(new ProfileModel("Name " + i, "text " + i, i));
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.profile_list_item, parent, false);
            holder = new ViewHolder();
            holder.title = convertView.findViewById(R.id.name);
            holder.info = convertView.findViewById(R.id.info);
            holder.icon = convertView.findViewById(R.id.icon);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ProfileModel qMenuItem = getItem(position);

        if (qMenuItem != null) {
            holder.title.setText(qMenuItem.getName());
            holder.info.setText(qMenuItem.getInfo());
        }


        return convertView;
    }

    static class ViewHolder {
        ImageView icon;
        TextView title;
        TextView info;
    }
}
