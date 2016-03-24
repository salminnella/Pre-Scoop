package com.example.anthony.prescoop.activity;

import android.content.Intent;
import android.database.Cursor;
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

public class DetailActivity extends AppCompatActivity {
    int id;
    TextView schoolAddressText;
    TextView schoolDescriptionText;
    TextView schoolPriceText;
    TextView schoolTypeText;
    TextView schoolRegionText;
    TextView schoolAgeGroup;
    ImageView schoolPrimaryImage;
    ImageView schoolRatingImage;
    ImageView schoolImage2;
    ImageButton favSchoolImageButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        receiveIntentExtras();

        initializeViews();

        populateSchoolDetails();

        setOnItemClickListener();

    }

    private void receiveIntentExtras() {

        // get the id from the clicked item in ListActivity
        id = getIntent().getIntExtra(ListActivity.RECORD_ID, -1);
    }

    private void populateSchoolDetails() {
        //get the database instance
        DatabaseHelper helper = DatabaseHelper.getInstance(DetailActivity.this);
        // get a preschool object using a cursor from the database
        PreSchool retrievedPreschool = helper.retrievePreschool(id);
        // set the fields using the preschool object
        schoolAddressText.setText(retrievedPreschool.getStreetAddress() + " " + retrievedPreschool.getCity() + " " + retrievedPreschool.getState() + ", " + retrievedPreschool.getZipCode());
        schoolDescriptionText.setText(retrievedPreschool.getSchoolDescription());
        schoolPriceText.setText("$" + String.valueOf(retrievedPreschool.getPrice()) + " /mo");
        if (retrievedPreschool.isFavorite() == 1) {
            favSchoolImageButton.setImageResource(R.drawable.ic_favorite_red_24dp);
        }
        schoolRatingImage.setImageResource(extractRating(retrievedPreschool.getRating()));
        schoolTypeText.setText(retrievedPreschool.getType());
        schoolRegionText.setText(retrievedPreschool.getRegion());
        schoolAgeGroup.setText(retrievedPreschool.getAgeGroup());
        schoolPrimaryImage.setImageResource(retrievedPreschool.getImages(0));
        schoolImage2.setImageResource(retrievedPreschool.getImages(1));
    }

    private void initializeViews() {
        schoolPrimaryImage = (ImageView) findViewById(R.id.default_school_image_detail);
        schoolAddressText = (TextView) findViewById(R.id.address_info_detail);
        schoolRatingImage = (ImageView) findViewById(R.id.rating_image_detail);
        schoolDescriptionText = (TextView) findViewById(R.id.description_info_detail);
        schoolImage2 = (ImageView) findViewById(R.id.school_photo2_detail);
        schoolPriceText = (TextView) findViewById(R.id.price_info_detail);
        favSchoolImageButton = (ImageButton) findViewById(R.id.favorite_school_image_detail);
        schoolRatingImage = (ImageView) findViewById(R.id.rating_image_detail);
        schoolTypeText = (TextView) findViewById(R.id.type_info_detail);
        schoolRegionText = (TextView) findViewById(R.id.region_info_detail);
        schoolAgeGroup = (TextView) findViewById(R.id.age_group_info_detail);

    }

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

    private void setOnItemClickListener() {
        favSchoolImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper helper = DatabaseHelper.getInstance(DetailActivity.this);
                Cursor cursor = helper.findPreschoolById(id);
                cursor.moveToFirst();
                if (cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_FAVORITE)) == 0) {
                    helper.setFavoriteSchool(id);
                    cursor = helper.findPreschoolById(id);
                    favSchoolImageButton.setImageResource(R.drawable.ic_favorite_red_24dp);
                } else {
                    //remove favorite school
                    helper.removeFavoriteSchool(id);
                    cursor = helper.findPreschoolById(id);
                    favSchoolImageButton.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                }

                cursor.close();
            }
        });
    }


    // filling toolbar with menu options, and setting the actions for them
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detail_activity, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // make sure the list is refreshed after user clicks back button
                Intent backToList = getIntent();
                if (backToList == null) {
                    break;
                }
                setResult(RESULT_OK, backToList);
                finish();
                break;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}
