package com.example.anthony.prescoop.models;

import android.content.Context;
import com.example.anthony.prescoop.R;
import java.util.ArrayList;

/**
 * Created by anthony on 3/24/16.
 */

/**
 * Main activity will check the database to see if its empty. If it is empty,
 * this class fills it with Preschools.
 */
public class PopulatePreschoolDB {

    // region private variables
    private static int[] acornImages = new int[5];
    private static String[] acornImageDescriptions = new String[5];

    private static int[] quarryLaneImages = new int[5];
    private static String[] quarryImageDescriptions = new String[5];

    private static int[] montessoriImages = new int[5];
    private static String[] montessoriImageDescriptions = new String[5];

    private static int[] sunsetImages = new int[5];
    private static String[] sunsetImageDescriptions = new String[5];

    private static int[] urbanitesImages = new int[5];
    private static String[] urbanitesImageDescription = new String[5];
    // endregion private variables

    /**
     * returns an array list of preschool objects, to be used to populate the database
     * only if the database is empty at runtime
     * @param context Context
     * @return ArrayList of PreSchool
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
        // urbanites school
        PreSchool urbanites = new PreSchool();

        // setting acorn values
        acorn.setName(context.getString(R.string.acorn_name));
        acorn.setSchoolDescription(R.string.acorn_description);
        acorn.setPrice(800);
        acorn.setStreetAddress("816 Diablo Rd");
        acorn.setCity("Danville");
        acorn.setState("CA");
        acorn.setZipCode("94526");
        acorn.setType("public");
        acorn.setRating(4);
        acorn.setRegion("East Bay");
        acorn.setRange(16);
        acorn.setPhoneNumber("925.837.1145");
        acorn.setAgeGroup("2-5 Years");
        // array for the images for acorn school
        acornImages[0] = R.drawable.acorn_main_photo;
        acornImages[1] = R.drawable.acorn_photo_two;
        acorn.setImages(acornImages);
        // array for the image descriptions for acorn school
        acornImageDescriptions[0] = context.getString(R.string.acorn_image1_description);
        acornImageDescriptions[1] = context.getString(R.string.acorn_image1_description);
        acorn.setImageDescription(acornImageDescriptions);


        // setting quarry lane school values
        quarryLane.setName("The Quarry Lane School");
        quarryLane.setSchoolDescription(R.string.quarry_description);
        quarryLane.setPrice(1400);
        quarryLane.setStreetAddress("3750 Boulder St.");
        quarryLane.setCity("Pleasanton");
        quarryLane.setState("CA");
        quarryLane.setZipCode("94568");
        quarryLane.setType("public");
        quarryLane.setRating(3);
        quarryLane.setRegion("East Bay");
        quarryLane.setRange(11);
        quarryLane.setPhoneNumber("(925) 462-6300");
        quarryLane.setAgeGroup("1-5 years");
        // array for the images for acorn school
        quarryLaneImages[0] = R.drawable.quarry_campus;
        quarryLaneImages[1] = R.drawable.quarry_campus_2;
        quarryLaneImages[2] = R.drawable.quarry_class;
        quarryLaneImages[3] = R.drawable.quaryy_computer_lab;
        quarryLane.setImages(quarryLaneImages);
        quarryImageDescriptions[0] = context.getString(R.string.defualt_image_description);
        quarryImageDescriptions[1] = context.getString(R.string.defualt_image_description);
        quarryImageDescriptions[2] = context.getString(R.string.defualt_image_description);
        quarryImageDescriptions[3] = context.getString(R.string.defualt_image_description);
        quarryLane.setImageDescription(quarryImageDescriptions);


        // setting montessori school values
        montessori.setName(context.getString(R.string.montessori_name));
        montessori.setSchoolDescription(R.string.sf_montesorri_description);
        montessori.setPrice(2700);
        montessori.setStreetAddress("1566 32nd Ave.");
        montessori.setCity("San Francisco");
        montessori.setState("CA");
        montessori.setZipCode("94122");
        montessori.setType("private");
        montessori.setRating(5);
        montessori.setRegion("San Francisco");
        montessori.setRange(6);
        montessori.setPhoneNumber("(415) 504-6467");
        montessori.setAgeGroup("3-5 years");
        montessoriImages[0] = R.drawable.montessori;
        montessori.setImages(montessoriImages);
        //TODO set new montesorri images
        montessoriImageDescriptions[0] = context.getString(R.string.defualt_image_description);
        montessoriImageDescriptions[1] = context.getString(R.string.defualt_image_description);
        montessori.setImageDescription(montessoriImageDescriptions);


        // settings sunset co-op school values
        sunset.setName("Sunset Co-op Nursery School");
        sunset.setSchoolDescription(R.string.sunset_co_op_description);
        sunset.setPrice(305);
        sunset.setStreetAddress("4245 Lawton St.");
        sunset.setCity("San Francisco");
        sunset.setState("CA");
        sunset.setZipCode("94122");
        sunset.setType("co-op");
        sunset.setRating(4);
        sunset.setRegion("San Francisco");
        sunset.setRange(4);
        sunset.setPhoneNumber("415.759.5710");
        sunset.setAgeGroup("2-5");
        sunsetImages[0] = R.drawable.sunset;
        sunset.setImages(sunsetImages);
        sunsetImageDescriptions[0] = context.getString(R.string.defualt_image_description);
        sunset.setImageDescription(sunsetImageDescriptions);

        // setting urbanites values
        urbanites.setName("Little Urbanites Preschool");
        urbanites.setSchoolDescription(R.string.urbanites_description);
        urbanites.setPrice(800);
        urbanites.setStreetAddress("1258 20th Avenue");
        urbanites.setCity("San Francisco");
        urbanites.setState("CA");
        urbanites.setZipCode("94122");
        urbanites.setType("public");
        urbanites.setRating(3);
        urbanites.setRegion("East Bay");
        urbanites.setRange(1);
        urbanites.setPhoneNumber("925.837.1145");
        urbanites.setAgeGroup("2-5 Years");
        // array for the images for acorn school
        urbanitesImages[0] = R.drawable.urbanites2;
        urbanitesImages[1] = R.drawable.urbanites_reading;
        urbanites.setImages(urbanitesImages);
        // array for the image descriptions for acorn school
        urbanitesImageDescription[0] = context.getString(R.string.defualt_image_description);
        urbanitesImageDescription[1] = context.getString(R.string.defualt_image_description);
        urbanites.setImageDescription(urbanitesImageDescription);

        // adding school objects to a list
        ArrayList<PreSchool> allPreSchools = new ArrayList<>();
        allPreSchools.add(acorn);
        allPreSchools.add(quarryLane);
        allPreSchools.add(montessori);
        allPreSchools.add(sunset);
        allPreSchools.add(urbanites);

        return allPreSchools;
    }
}
