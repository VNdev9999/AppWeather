package com.example.appweather.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appweather.R;

import java.util.List;

public class CategoryAdapter extends ArrayAdapter {

    public CategoryAdapter(@NonNull Context context, int resource, @NonNull List<String> name) {
        super(context, resource, name);
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected, parent, false);
        TextView tvSelected = convertView.findViewById(R.id.tv_selected);
        String name = (String) this.getItem(position);
        if (name != null || !name.isEmpty()) {
            tvSelected.setText(name);
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_city, parent, false);
        TextView tv = convertView.findViewById(R.id.tv_category);
        String name = (String) this.getItem(position);
        if (name != null || !name.isEmpty()) {
            tv.setText(name);
        }
        return convertView;
    }
}
