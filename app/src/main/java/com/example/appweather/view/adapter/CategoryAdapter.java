package com.example.appweather.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appweather.R;
import com.example.appweather.databinding.ItemCategoryCityBinding;
import com.example.appweather.databinding.ItemSelectedBinding;

import java.util.List;

public class CategoryAdapter extends ArrayAdapter {
    private Context context;
    private List<String> nameList;

    public CategoryAdapter(@NonNull Context context, int resource, @NonNull List<String> name) {
        super(context, resource, name);
        this.context = context;
        this.nameList = name;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ItemSelectedBinding binding;
        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            binding = ItemSelectedBinding.inflate(inflater, parent, false);
            convertView = binding.getRoot();
        } else {
            binding = ItemSelectedBinding.bind(convertView);
        }
        String name = nameList.get(position);
        binding.tvSelected.setText(name);
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ItemCategoryCityBinding binding;
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_city, parent, false);
        binding = ItemCategoryCityBinding.bind(convertView);
        String name = (String) this.getItem(position);
        if (name != null || !name.isEmpty()) {
            binding.tvCategory.setText(name);
        }
        return convertView;
    }
}
