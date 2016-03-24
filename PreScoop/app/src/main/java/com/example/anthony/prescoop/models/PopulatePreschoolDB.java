package com.example.anthony.prescoop.models;

import android.content.Context;

import com.example.anthony.prescoop.R;

import java.util.ArrayList;

/**
 * Created by anthony on 3/24/16.
 */
public class PopulatePreschoolDB {

    /**
     * returns an array list of preschool objects, to be used to populate the database
     * only if the database is empty at runtime
     * @param context
     * @return
     */
    public static ArrayList<PreSchool> getPreSchoolItems(Context context) {
        ArrayList<PreSchool> preSchools = new ArrayList<>();

        preSchools.addAll(fillPreschools(context));

        return preSchools;
    }

    public static ArrayList<PreSchool> fillPreschools(Context context) {
        //acorn preschool
        PreSchool acorn = new PreSchool();
        // quarry lane school
        PreSchool quarryLane = new PreSchool();
        // montessori school
        PreSchool montessori = new PreSchool();
        // sunset school
        PreSchool sunset = new PreSchool();


        // a list of the images for acorn school
        int[] acornImages = new int[5];
        acornImages[0] = R.drawable.acorn_main_photo;
        acornImages[1] = R.drawable.acorn_photo_two;

        // list of images for montessori school
        int[] montessoriImages = new int[5];
        //TODO need to add the school photos

        // list of images of quarry lane school
        int[] quarryLaneImages = new int[5];
        // TODO: need to add the school photos

        // list of images of sunset school
        int[] sunsetImages = new int[5];
        //TODO need to add the school photos


        // lists of image descriptiosn for each school
        String[] acornImageDescriptions = new String[5];
        acornImageDescriptions[0] = context.getString(R.string.acorn_image1_description);
        acornImageDescriptions[1] = context.getString(R.string.acorn_image1_description);

        String[] quarryImageDescriptions = new String[5];
        quarryImageDescriptions[0] = context.getString(R.string.acorn_image1_description);
        quarryImageDescriptions[1] = context.getString(R.string.acorn_image1_description);

        String[] montessoriImageDescriptions = new String[5];
        montessoriImageDescriptions[0] = context.getString(R.string.acorn_image1_description);
        montessoriImageDescriptions[1] = context.getString(R.string.acorn_image1_description);

        String[] sunsetImageDescriptions = new String[5];
        sunsetImageDescriptions[0] = context.getString(R.string.acorn_image1_description);
        sunsetImageDescriptions[1] = context.getString(R.string.acorn_image1_description);

        // setting acorn values
        acorn.setName(context.getString(R.string.acorn_name));
        acorn.setSchoolDescription(R.string.acorn_description);
        acorn.setPrice(800.00);
        acorn.setStreetAddress("816 Diablo Rd");
        acorn.setCity("Danville");
        acorn.setState("CA");
        acorn.setZipCode("94526");
        acorn.setType("public");
        acorn.setRating(4);
        acorn.setRegion("Easy Bay");
        acorn.setRange(16);
        acorn.setPhoneNumber("925.837.1145");
        acorn.setAgeGroup("2-5 Years");
        acorn.setImages(acornImages);
        acorn.setImageDescription(acornImageDescriptions);

        // setting quarry lane school values
        quarryLane.setName("The Quarry Lane School");
        quarryLane.setSchoolDescription(R.string.quarry_description);
        quarryLane.setPrice(1400.00);
        quarryLane.setStreetAddress("3750 Boulder St.");
        quarryLane.setCity("Pleasanton");
        quarryLane.setState("CA");
        quarryLane.setZipCode("94568");
        quarryLane.setType("public");
        quarryLane.setRating(3);
        quarryLane.setRegion("Easy Bay");
        quarryLane.setRange(11);
        quarryLane.setPhoneNumber("Phone Number");
        quarryLane.setAgeGroup("Age Group");
        quarryLane.setImages(quarryLaneImages);
        quarryLane.setImageDescription(quarryImageDescriptions);

        // setting montessori school values
        montessori.setName(context.getString(R.string.montessori_name));
        montessori.setSchoolDescription(R.string.montessori_name);
        montessori.setPrice(2700.00);
        montessori.setStreetAddress("1566 32nd Ave.");
        montessori.setCity("San Francisco");
        montessori.setState("CA");
        montessori.setZipCode("94122");
        montessori.setType("private");
        montessori.setRating(5);
        montessori.setRegion("San Francisco");
        montessori.setRange(6);
        montessori.setPhoneNumber("Phone Number");
        montessori.setAgeGroup("Age Group");
        montessori.setImages(montessoriImages);
        montessori.setImageDescription(montessoriImageDescriptions);

        // settings sunset co-op school values
        sunset.setName("Sunset Co-op Nursery School");
        sunset.setSchoolDescription(R.string.sunset_co_op_description);
        sunset.setPrice(305.00);
        sunset.setStreetAddress("4245 Lawton St.");
        sunset.setCity("San Francisco");
        sunset.setState("CA");
        sunset.setZipCode("94122");
        sunset.setType("co-op");
        sunset.setRating(4);
        sunset.setRegion("San Francisco");
        sunset.setRange(4);
        sunset.setPhoneNumber("phone number");
        sunset.setAgeGroup("Age Group");
        sunset.setImages(sunsetImages);
        sunset.setImageDescription(sunsetImageDescriptions);

        // adding school objects to a list
        ArrayList<PreSchool> allPreSchools = new ArrayList<>();
        allPreSchools.add(acorn);
        allPreSchools.add(quarryLane);
        allPreSchools.add(montessori);
        allPreSchools.add(sunset);

        return allPreSchools;
    }
}
