package com.example.anthony.prescoop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anthony.prescoop.DBHelper.DatabaseHelper;
import com.example.anthony.prescoop.R;
import com.example.anthony.prescoop.models.PreSchool;

/**
 *  Displays all the details for the selected Preschool from the ListActivity
 */
public class DetailActivity extends AppCompatActivity {
    //region private Variables
    private int id;
    private TextView schoolAddressText;
    private TextView schoolDescriptionText;
    private TextView schoolPriceText;
    private TextView schoolTypeText;
    private TextView schoolRegionText;
    private TextView schoolAgeGroupText;
    private TextView schoolImage2Descrption;
    private TextView schoolImage3Descrption;
    private ImageView schoolPrimaryImage;

    private ImageView schoolRatingImage;
    private ImageView schoolImage2;
    private ImageView schoolImage3;
    private ImageButton favSchoolImageButton;

    private DatabaseHelper searchHelper;
    // endregion private variables

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        searchHelper = DatabaseHelper.getInstance(DetailActivity.this);

        receiveIntentFromList();

        initializeViews();

        populateSchoolDetails();

        setClickListener();

    }

    /**
     * Receives the id of the selected Preschool from the List activity (search results)
     */
    private void receiveIntentFromList() {

        // get the id from the clicked item in ListActivity
        id = getIntent().getIntExtra(ListActivity.RECORD_ID, -1);
    }

    /**
     * initializes all the views in the layout
     */
    private void initializeViews() {
        // TextView
        schoolAddressText = (TextView) findViewById(R.id.address_info_detail);
        schoolDescriptionText = (TextView) findViewById(R.id.description_info_detail);
        schoolPriceText = (TextView) findViewById(R.id.price_info_detail);
        schoolTypeText = (TextView) findViewById(R.id.type_info_detail);
        schoolRegionText = (TextView) findViewById(R.id.region_info_detail);
        schoolAgeGroupText = (TextView) findViewById(R.id.age_group_info_detail);
        schoolImage2Descrption = (TextView) findViewById(R.id.school_photo2_description_detail);
        schoolImage3Descrption = (TextView) findViewById(R.id.school_photo3_description_detail);

        // ImageView
        schoolPrimaryImage = (ImageView) findViewById(R.id.default_school_image_detail);
        schoolRatingImage = (ImageView) findViewById(R.id.rating_image_detail);
        schoolImage2 = (ImageView) findViewById(R.id.school_photo2_detail);
        schoolImage3 = (ImageView) findViewById(R.id.school_photo3_detail);
        schoolRatingImage = (ImageView) findViewById(R.id.rating_image_detail);

        // ImageButton
        favSchoolImageButton = (ImageButton) findViewById(R.id.favorite_school_image_detail);

    }

    /**
     * Finds the Preschool using the id received from the intent.
     * Fills in all the Preschool item details.
     */
    private void populateSchoolDetails() {
        // get a preschool object from the database
        PreSchool retrievedPreschool = searchHelper.retrievePreschool(id);
        String formattedAddress = retrievedPreschool.getStreetAddress() + " " + retrievedPreschool.getCity() + " " + retrievedPreschool.getState() + ", " + retrievedPreschool.getZipCode();
        String formattedPrice = "$" + String.valueOf(retrievedPreschool.getPrice()) + " /mo";
        // set the fields using the preschool object
        schoolAddressText.setText(formattedAddress);
        schoolDescriptionText.setText(retrievedPreschool.getSchoolDescription());
        schoolPriceText.setText(formattedPrice);
        schoolTypeText.setText(retrievedPreschool.getType());
        schoolRegionText.setText(retrievedPreschool.getRegion());
        schoolAgeGroupText.setText(retrievedPreschool.getAgeGroup());
        if (retrievedPreschool.getImageDescription() != null) {
            schoolImage2Descrption.setText(retrievedPreschool.getImageDescription()[1]);
            schoolImage3Descrption.setText(retrievedPreschool.getImageDescription()[2]);
        }

        // if its a favorite, the fav icon will be filled red. its just an outline image if not a favorite
        if (retrievedPreschool.getFavorite() == 1) {
            favSchoolImageButton.setImageResource(R.drawable.ic_favorite_red_24dp);
        }
        // all the images for the school
        schoolPrimaryImage.setImageResource(retrievedPreschool.getImageByPostion(0));
        schoolImage2.setImageResource(retrievedPreschool.getImageByPostion(1));
        schoolImage3.setImageResource(retrievedPreschool.getImageByPostion(2));

        // the rating for the school is just holds a 1-5 integer. the extractRating function
        // puts in the right image for that number.
        schoolRatingImage.setImageResource(extractRating(retrievedPreschool.getRating()));
    }

    /**
     * Takes the 1-5 school rating, and returns the equivalent star rating image for that number.
     *
     * @param rating Integer type, from 1-5
     * @return Integer value of the star ratings image
     */
    private int extractRating(int rating) {
        int result;
        switch (rating) {
            case 1:
                result = R.drawable.one_star;
                break;
            case 2:
                result = R.drawable.two_stars;
                break;
            case 3:
                result = R.drawable.three_stars;
                break;
            case 4:
                result = R.drawable.four_stars;
                break;
            case 5:
                result = R.drawable.four_stars;
                break;
            default:
                result = R.drawable.pixel;
                break;
        }
        return result;
    }

    /**
     * Allows the user to select and remove the school as a favorite
     */
    private void setClickListener() {
        favSchoolImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreSchool retrievedPreschool = searchHelper.retrievePreschool(id);
                // sets the favorite column in the database to 1 --> adds it as a favorite
                if (retrievedPreschool.getFavorite() == 0) {
                    searchHelper.setFavoriteSchool(id);
                    // fills in the fav icon to red
                    favSchoolImageButton.setImageResource(R.drawable.ic_favorite_red_24dp);
                } else {
                    // sets the favorite column in the database to 0 --> removes favorite school
                    searchHelper.removeFavoriteSchool(id);
                    // sets the favorite icon back to just the outline
                    favSchoolImageButton.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                }
            }
        });
    }


    /**
     * filling the actionbar menu
     * @param menu Menu
     * @return true after layout inflation
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detail_activity, menu);

        return true;
    }

    /**
     * Back press will refresh the list results.
     * Is necessary in case user had selected a new favorite.
     * The new list results will then show the fav icon in the row
     * @param item MenuItem
     * @return true
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // make sure the list is refreshed after user clicks back button
                Intent intentToListResults = getIntent();
                if (intentToListResults == null) {
                    break;
                }
                setResult(RESULT_OK, intentToListResults);
                finish();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    /**
     * overrides back button on device if pressed
     */
    @Override
    public void onBackPressed() {
        Intent intentToListResults = getIntent();
        if (intentToListResults == null) {
            return;
        }
        setResult(RESULT_OK, intentToListResults);
        finish();
    }
}