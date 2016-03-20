package com.example.anthony.prescoop.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true); //enables the up/back button in actionbar


        // get the id from the clicked item in ListActivity
        id = getIntent().getIntExtra(ListActivity.RECORD_ID, -1);

        initializeViews();
        populateSchoolDetails();



    }

    private void populateSchoolDetails() {
        //get the database instance
        DatabaseHelper helper = DatabaseHelper.getInstance(DetailActivity.this);
        // get a preschool object using a cursor from the database
        PreSchool retrievedPreschool = helper.retrievePreschool(id);
        // set the fields using the preschool object
        schoolAddress.setText(retrievedPreschool.getStreetAddress());
        schoolDescription.setText(retrievedPreschool.getDescription());
        defaultSchoolImage.setImageResource(retrievedPreschool.getImages(0));
        schoolImage2.setImageResource(retrievedPreschool.getImages(1));


    }

    private void initializeViews() {
        defaultSchoolImage = (ImageView) findViewById(R.id.default_school_image_detail);
        schoolAddress = (TextView) findViewById(R.id.address_info_detail);
        schoolRating = (ImageView) findViewById(R.id.rating_image_detail);
        schoolDescription = (TextView) findViewById(R.id.description_info_detail);
        schoolImage2 = (ImageView) findViewById(R.id.school_photo2_detail);

    }

//    private Cursor retreiveSchoolDetails(int id) {
//
//        // get the database instance
//        DatabaseHelper helper = DatabaseHelper.getInstance(DetailActivity.this);
//        Cursor cursor = null;
//
//        if (id > 0) {
//            cursor = helper.findPreschoolById(id);
//        }
//        //TODO still need a null check on the cursor
//        return cursor;
//
//    }


    // filling toolbar with menu options, and setting the actions for them
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }
}
