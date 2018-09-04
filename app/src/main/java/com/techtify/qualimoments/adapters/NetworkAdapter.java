package com.techtify.qualimoments.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.techtify.qualimoments.R;
import com.techtify.qualimoments.custom.RoundedImageView;
import com.techtify.qualimoments.model.NetworkItem;

import java.util.ArrayList;


public class NetworkAdapter extends BaseAdapter implements Filterable {

    private final ArrayList<NetworkItem> original = new ArrayList<>();
    private final ArrayList<NetworkItem> items;
    private LayoutInflater layoutInflater;

    // 1
    public NetworkAdapter(Context context, ArrayList<NetworkItem> items) {
        this.items = items;
        layoutInflater = LayoutInflater.from(context);
        original.addAll(items);
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
    public NetworkItem getItem(int position) {
        return items.get(position);
    }

    // 5
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.network_list_item,parent,false);
            holder = new ViewHolder();
            holder.title = convertView.findViewById(R.id.text);
            holder.icon = convertView.findViewById(R.id.icon);
            holder.tag = convertView.findViewById(R.id.tag);
            holder.connect = convertView.findViewById(R.id.connect);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        NetworkItem item = getItem(position);

        holder.title.setText(item.getName());
        holder.icon.setImageResource(item.getResId());
        holder.tag.setText(item.getTag());


        return convertView;
    }

    @Override
    public Filter getFilter() {

        return new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                items.clear();
                items.addAll((ArrayList<NetworkItem>) results.values);

                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();
                ArrayList<NetworkItem> FilteredArrayNames = new ArrayList<>();


                if (constraint.length() != 0) {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < original.size(); i++) {
                        String dataName = original.get(i).getName();
                        String datTag = original.get(i).getTag().replace(" ", "");
                        if (dataName.toLowerCase().contains(constraint.toString()) || datTag.toLowerCase().contains(constraint.toString())) {
                            FilteredArrayNames.add(original.get(i));
                        }
                    }
                } else {
                    FilteredArrayNames.addAll(original);
                }
                results.count = FilteredArrayNames.size();
                results.values = FilteredArrayNames;

                return results;
            }
        };
    }


    static class ViewHolder {
        TextView title;
        RoundedImageView icon;
        TextView tag;
        TextView connect;
    }
}
