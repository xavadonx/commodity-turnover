package com.example.zer.task3_2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {

    private TextView name;
    private TextView count;

    public ProductAdapter(Context context, List<Product> products) {
        super(context, R.layout.list_item, products);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = layoutInflater.inflate(R.layout.list_item, parent, false);

        name = (TextView) rowView.findViewById(R.id.li_name);
        count = (TextView) rowView.findViewById(R.id.li_count);

        name.setText(getItem(position).getName());
        count.setText(getItem(position).getCount().toString());

        return rowView;
    }
}
