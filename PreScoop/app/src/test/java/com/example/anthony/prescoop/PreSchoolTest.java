package com.example.anthony.prescoop;

import com.example.anthony.prescoop.models.PreSchool;

import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.*;

/**
 * Created by anthony on 3/23/16.
 */
public class PreSchoolTest {

    public ArrayList<Integer> schoolImages() {
        ArrayList<Integer> schoolImages = new ArrayList<>();
        schoolImages.add(R.drawable.acorn_main_photo);
        schoolImages.add(R.drawable.acorn_photo_two);

        return schoolImages;
    }


    PreSchool preSchool = new PreSchool("Acorn Learning Center", R.string.acorn_description, 800,"3918 Fulton St", "San Francisco", "Ca", "94118", "5204403178", "san francisco", 15, "public", "2-5", 5, 1, schoolImages());


    // testing if the correct info is being pulled into the list results
    @Test
    public void testGetSchoolName() {
        String expectedResult = "Acorn Learning Center";
        String actualResult = preSchool.getName();

        assertEquals(expectedResult, actualResult);
    }

    // testing if the correct info is being pulled into the list results
    @Test
    public void testGetFullAddress() {
        String expectedResult = "3918 Fulton St San Francisco Ca, 94118";
        String actualResult = preSchool.getStreetAddress() + " " + preSchool.getCity() + " " + preSchool.getState() + ", " + preSchool.getZipCode();

        assertEquals(expectedResult, actualResult);
    }

    // testing if the correct info is being pulled into the list results
    @Test
    public void testIsFavorite() {
        int expectedResult = 1;
        int actualResult = preSchool.isFavorite();

        assertEquals(expectedResult, actualResult);

        int expectedResult2 = 0;
        preSchool.setFavorite(0);
        int actualResult2 = preSchool.isFavorite();

        assertEquals(expectedResult2, actualResult2);
    }

    @Test
    public void testRangeSpinner() {
        int expectedResult = 15;
        int actualResult = preSchool.getRange();

        assertEquals(expectedResult, actualResult);
    }





    // testing if the photos and description details are correct in the details activity
    @Test
    public void testGetSchoolPhotos() {
        int expectedResult = R.drawable.acorn_main_photo;
        int actualResult = preSchool.getImages(0);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGetMoreSchoolPhotos() {

        int expectedResult = R.drawable.acorn_photo_two;
        int actualResult = preSchool.getImages(1);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGetSchoolDescription() {
        int expectedResult = R.string.acorn_description;
        int actualResult = preSchool.getSchoolDescription();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGetSchoolRating() {
        int expectedResult = 5;
        int actualResult = preSchool.getRating();

        assertEquals(expectedResult, actualResult);
    }

}
