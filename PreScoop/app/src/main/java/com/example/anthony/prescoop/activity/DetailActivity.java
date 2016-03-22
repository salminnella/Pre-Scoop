package com.example.anthony.prescoop.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anthony.prescoop.DBHelper.DatabaseHelper;
import com.example.anthony.prescoop.R;
import com.example.anthony.prescoop.models.PreSchool;

public class DetailActivity extends AppCompatActivity {
    int id;
    ImageView defaultSchoolImage;
    TextView schoolAddress;
    ImageView schoolRating;
    TextView schoolDescription;
    ImageView schoolImage2;

    TextView favorite;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
//        ActionBar actionBar = getActionBar();
//        actionBar.setDisplayShowTitleEnabled(false);

        receiveIntentExtras();

        initializeViews();

        populateSchoolDetails();

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
        schoolAddress.setText(retrievedPreschool.getStreetAddress());
        schoolDescription.setText(retrievedPreschool.getSchoolDescription());
        favorite.setText(String.valueOf(retrievedPreschool.isFavorite()));
        defaultSchoolImage.setImageResource(retrievedPreschool.getImages(0));
        schoolImage2.setImageResource(retrievedPreschool.getImages(1));
    }

    private void initializeViews() {
        defaultSchoolImage = (ImageView) findViewById(R.id.default_school_image_detail);
        schoolAddress = (TextView) findViewById(R.id.address_info_detail);
        schoolRating = (ImageView) findViewById(R.id.rating_image_detail);
        schoolDescription = (TextView) findViewById(R.id.description_info_detail);
        schoolImage2 = (ImageView) findViewById(R.id.school_photo2_detail);

        favorite = (TextView) findViewById(R.id.favorite_school_detail);

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
            case R.id.action_favorite:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                DatabaseHelper helper = DatabaseHelper.getInstance(DetailActivity.this);
                Cursor cursor = helper.findPreschoolById(id);
                cursor.moveToFirst();
                if (cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_FAVORITE)) == 0) {
                    helper.setFavoriteSchool(id);
                    cursor = helper.findPreschoolById(id);
                    favorite.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_FAVORITE)));
                } else {
                    //remove favorite school
                    helper.removeFavoriteSchool(id);
                    cursor = helper.findPreschoolById(id);
                    favorite.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_FAVORITE)));
                }

                cursor.close();
                break;

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
