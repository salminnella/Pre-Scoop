package com.example.anthony.prescoop.models;

/**
 * Created by anthony on 3/12/16.
 */
public class Rating {
    int array_images[];

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
