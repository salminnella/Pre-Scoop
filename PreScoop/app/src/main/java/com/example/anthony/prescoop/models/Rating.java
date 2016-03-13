package com.example.anthony.prescoop.models;

import com.example.anthony.prescoop.R;

/**
 * Created by anthony on 3/12/16.
 */
public class Rating {
    int array_images[] = {R.drawable.one_star,
            R.drawable.two_stars, R.drawable.three_stars,
            R.drawable.four_stars, R.drawable.five_stars};

    public Rating(int[] arr_images) {
        this.array_images = arr_images;
    }

    public int[] getArray_images() {
        return array_images;
    }

    public void setArray_images(int[] array_images) {
        this.array_images = array_images;
    }
}
