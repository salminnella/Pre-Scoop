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

    private static int[] imagineImages = new int[5];
    private static String[] imagineImageDescription = new String[5];
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
        // imagine school
        PreSchool imagine = new PreSchool();

        // setting acorn values
        acorn.setName(context.getString(R.string.acorn_name));
        acorn.setSchoolDescription(R.string.acorn_description);
        acorn.setPrice(800);
        acorn.setStreetAddress(context.getString(R.string.acorn_street_address));
        acorn.setCity(context.getString(R.string.acorn_city));
        acorn.setState(context.getString(R.string.school_state));
        acorn.setZipCode(context.getString(R.string.acorn_zipcode));
        acorn.setType(context.getString(R.string.public_type));
        acorn.setRating(4);
        acorn.setRegion(context.getString(R.string.acorn_region));
        acorn.setRange(16);
        acorn.setPhoneNumber(context.getString(R.string.acorn_phoneNum));
        acorn.setAgeGroup(context.getString(R.string.acorn_ageGroup));
        acorn.setFavorite(1);
        // array for the images for acorn school
        acornImages[0] = R.drawable.acorn_main_photo;
        acornImages[1] = R.drawable.acorn_photo_two;
        acorn.setImages(acornImages);
        // array for the image descriptions for acorn school
        acornImageDescriptions[0] = context.getString(R.string.acorn_image1_description);
        acornImageDescriptions[1] = context.getString(R.string.acorn_image1_description);
        acorn.setImageDescription(acornImageDescriptions);


        // setting quarry lane school values
        quarryLane.setName(context.getString(R.string.quarry_name));
        quarryLane.setSchoolDescription(R.string.quarry_description);
        quarryLane.setPrice(1400);
        quarryLane.setStreetAddress(context.getString(R.string.quarry_street_address));
        quarryLane.setCity(context.getString(R.string.quarry_city));
        quarryLane.setState(context.getString(R.string.school_state));
        quarryLane.setZipCode(context.getString(R.string.quarry_zipcode));
        quarryLane.setType(context.getString(R.string.public_type));
        quarryLane.setRating(3);
        quarryLane.setRegion(context.getString(R.string.quarry_region));
        quarryLane.setRange(11);
        quarryLane.setPhoneNumber(context.getString(R.string.quarry_phoneNum));
        quarryLane.setAgeGroup(context.getString(R.string.quarry_ageGroup));
        quarryLane.setFavorite(1);
        // array for the images for quarry school
        quarryLaneImages[0] = R.drawable.quarry_campus;
        quarryLaneImages[1] = R.drawable.quarry_campus_2;
        quarryLaneImages[2] = R.drawable.quarry_class;
        quarryLaneImages[3] = R.drawable.quaryy_computer_lab;
        quarryLane.setImages(quarryLaneImages);
        // array for the image descriptions for quarry school
        quarryImageDescriptions[0] = context.getString(R.string.defualt_image_description);
        quarryImageDescriptions[1] = context.getString(R.string.defualt_image_description);
        quarryImageDescriptions[2] = context.getString(R.string.defualt_image_description);
        quarryImageDescriptions[3] = context.getString(R.string.defualt_image_description);
        quarryLane.setImageDescription(quarryImageDescriptions);


        // setting montessori school values
        montessori.setName(context.getString(R.string.montessori_name));
        montessori.setSchoolDescription(R.string.sf_montesorri_description);
        montessori.setPrice(2700);
        montessori.setStreetAddress(context.getString(R.string.montessori_street_address));
        montessori.setCity(context.getString(R.string.montessori_city));
        montessori.setState(context.getString(R.string.school_state));
        montessori.setZipCode(context.getString(R.string.montessori_zipcode));
        montessori.setType(context.getString(R.string.private_type));
        montessori.setRating(5);
        montessori.setRegion(context.getString(R.string.montessori_region));
        montessori.setRange(6);
        montessori.setPhoneNumber(context.getString(R.string.montessori_phone));
        montessori.setAgeGroup(context.getString(R.string.montessori_ageGroup));
        montessori.setFavorite(1);
        // array for the images for montessori school
        montessoriImages[0] = R.drawable.montessori;
        montessori.setImages(montessoriImages);
        // array for the image description for montessori school
        montessoriImageDescriptions[0] = context.getString(R.string.defualt_image_description);
        montessori.setImageDescription(montessoriImageDescriptions);


        // settings sunset co-op school values
        sunset.setName(context.getString(R.string.sunset_name));
        sunset.setSchoolDescription(R.string.sunset_co_op_description);
        sunset.setPrice(305);
        sunset.setStreetAddress(context.getString(R.string.sunset_street_address));
        sunset.setCity(context.getString(R.string.sunset_city));
        sunset.setState(context.getString(R.string.school_state));
        sunset.setZipCode(context.getString(R.string.sunset_zipcode));
        sunset.setType(context.getString(R.string.co_op_type));
        sunset.setRating(4);
        sunset.setRegion(context.getString(R.string.sunset_region));
        sunset.setRange(4);
        sunset.setPhoneNumber(context.getString(R.string.sunset_phoneNum));
        sunset.setAgeGroup(context.getString(R.string.sunset_ageGroup));
        // array for the images for sunset school
        sunsetImages[0] = R.drawable.sunset;
        sunset.setImages(sunsetImages);
        // array for the image description for sunset school
        sunsetImageDescriptions[0] = context.getString(R.string.defualt_image_description);
        sunset.setImageDescription(sunsetImageDescriptions);

        // setting urbanites values
        urbanites.setName(context.getString(R.string.urbanites_name));
        urbanites.setSchoolDescription(R.string.urbanites_description);
        urbanites.setPrice(800);
        urbanites.setStreetAddress(context.getString(R.string.urbanites_street_address));
        urbanites.setCity(context.getString(R.string.urbanites_city));
        urbanites.setState(context.getString(R.string.urbanites_state));
        urbanites.setZipCode(context.getString(R.string.urbanites_zipcode));
        urbanites.setType(context.getString(R.string.public_type));
        urbanites.setRating(3);
        urbanites.setRegion(context.getString(R.string.urbanites_region));
        urbanites.setRange(1);
        urbanites.setPhoneNumber(context.getString(R.string.urbanites_phoneNum));
        urbanites.setAgeGroup(context.getString(R.string.urbanites_ageGroup));
        // array for the images for urbanites school
        urbanitesImages[0] = R.drawable.urbanites2;
        urbanitesImages[1] = R.drawable.urbanites_reading;
        urbanites.setImages(urbanitesImages);
        // array for the image descriptions for urbanites school
        urbanitesImageDescription[0] = context.getString(R.string.defualt_image_description);
        urbanitesImageDescription[1] = context.getString(R.string.defualt_image_description);
        urbanites.setImageDescription(urbanitesImageDescription);

        // setting imagine values
        imagine.setName(context.getString(R.string.imagine_name));
        imagine.setSchoolDescription(R.string.imagine_description);
        imagine.setPrice(1208);
        imagine.setStreetAddress(context.getString(R.string.imagine_street_address));
        imagine.setCity(context.getString(R.string.imagine_city));
        imagine.setState(context.getString(R.string.imagine_state));
        imagine.setZipCode(context.getString(R.string.imagine_zipcode));
        imagine.setType(context.getString(R.string.public_type));
        imagine.setRating(1);
        imagine.setRegion(context.getString(R.string.imagine_region));
        imagine.setRange(4);
        imagine.setPhoneNumber(context.getString(R.string.imagine_phoneNum));
        imagine.setAgeGroup(context.getString(R.string.imagine_ageGroup));
        // array for the images for imagine school
        imagineImages[0] = R.drawable.imagine;
        imagineImages[1] = R.drawable.imagine2;
        imagine.setImages(imagineImages);
        // array for the image descriptions for imagine school
        imagineImageDescription[0] = context.getString(R.string.defualt_image_description);
        imagineImageDescription[1] = context.getString(R.string.defualt_image_description);
        imagine.setImageDescription(imagineImageDescription);

        // adding school objects to a list
        ArrayList<PreSchool> allPreSchools = new ArrayList<>();
        allPreSchools.add(acorn);
        allPreSchools.add(quarryLane);
        allPreSchools.add(montessori);
        allPreSchools.add(sunset);
        allPreSchools.add(urbanites);
        allPreSchools.add(imagine);

        return allPreSchools;
    }
}
