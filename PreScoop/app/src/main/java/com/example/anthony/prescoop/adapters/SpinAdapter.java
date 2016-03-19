package com.example.anthony.prescoop.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.anthony.prescoop.R;

/**
 * Created by anthony on 3/10/16.
 */
public class SpinAdapter extends ArrayAdapter<Integer> {
    private Integer[] images;

    public SpinAdapter(Context context, Integer[] images) {
        super(context, R.layout.spinner_row, images);
        this.images = images;
    }

    @Override
    public View getDropDownView(int position, View convertView,ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {

        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_row, parent, false);

        ImageView stars = (ImageView)rowItem.findViewById(R.id.stars);
        stars.setImageResource(images[position]);

        return rowItem;
    }
}
