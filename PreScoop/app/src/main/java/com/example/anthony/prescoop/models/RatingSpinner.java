package com.example.anthony.prescoop.models;

/**
 * Created by anthony on 3/12/16.
 */
public class RatingSpinner {
    int arr_images[];

    public RatingSpinner(int[] arr_images) {
        this.arr_images = arr_images;
    }

    public int[] getArr_images() {
        return arr_images;
    }

    public void setArr_images(int[] arr_images) {
        this.arr_images = arr_images;
    }
}
