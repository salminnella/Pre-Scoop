package com.example.anthony.prescoop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

/**
 * Created by anthony on 3/10/16.
 */
public class SpinAdapter extends ArrayAdapter {
    int arr_images[] = { R.drawable.one_star,
            R.drawable.two_stars, R.drawable.three_stars,
            R.drawable.four_stars, R.drawable.five_stars};


    public SpinAdapter(Context context, int resource) {
        super(context, resource);
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
        stars.setImageResource(arr_images[position]);

        return rowItem;
    }




}
