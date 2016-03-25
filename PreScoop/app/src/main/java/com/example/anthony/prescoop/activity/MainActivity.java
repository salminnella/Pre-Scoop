package com.example.anthony.prescoop.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.anthony.prescoop.DBHelper.DatabaseHelper;
import com.example.anthony.prescoop.R;
import com.example.anthony.prescoop.adapters.SpinAdapter;
import com.example.anthony.prescoop.models.PopulatePreschoolDB;
import com.example.anthony.prescoop.models.PreSchool;
import com.example.anthony.prescoop.models.SearchDataForDB;

import java.util.ArrayList;

/**
 * Application begins here. The activity provides 4 to use as search criteria
 * that will be used in finding preschools from the database
 */
public class MainActivity extends AppCompatActivity {
    // region Constants
    private static final String TAG = "MainActivity";
    public static final String FIND_FAVORITE_PRESCHOOLS = "findFavs";
    public static final Boolean FAVORITES_VALUE = true;
    // endregion Constants

    //region private variables
    private EditText priceEdit;
    private EditText schoolNameEdit;
    private Spinner rangeSpinner;
    private ArrayAdapter<CharSequence> rangeAdapter;
    private Spinner ratingSpinner;
    private SpinAdapter ratingAdapter;
    private Button search;
    DatabaseHelper searchHelper;
    Cursor cursor;
    // endregion private variables

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchHelper = DatabaseHelper.getInstance(MainActivity.this);

        initViews();

        initClickListenerAndDoSearch();

        fillDatabaseWithPreschools();
    }

    /**
     * initialize all views that are used in the activity,
     * as well as the adapters for the 2 drop down spinners.
     */
    private void initViews() {
        priceEdit = (EditText) findViewById(R.id.price_main_edit);
        schoolNameEdit = (EditText) findViewById(R.id.school_name_main_edit);
        search = (Button) findViewById(R.id.search_button);

        // for the range spinner, to select a minimum distance for schools (within 5 miles etc.)
        rangeSpinner = (Spinner) findViewById(R.id.range_spinner_main);
        rangeAdapter = ArrayAdapter.createFromResource(this, R.array.range_array, android.R.layout.simple_spinner_item);
        rangeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rangeSpinner.setAdapter(rangeAdapter);

        // for the rating spinner, filled with images to select a minimum rating ( 1 star, 2 stars etc.)
        ratingSpinner = (Spinner) findViewById(R.id.rating_spinner_main);
        ratingAdapter = new SpinAdapter(MainActivity.this, new Integer[]{R.drawable.pixel, R.drawable.one_star, R.drawable.two_stars, R.drawable.three_stars, R.drawable.four_stars, R.drawable.five_stars});
        ratingSpinner.setAdapter(ratingAdapter);
    }

    /**
     * Set the click listener on the Search button.
     * Also performs the search, after filling intentToListResults
     * with search criteria in extras
     */
    private void initClickListenerAndDoSearch() {
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SearchDataForDB searchData = new SearchDataForDB(schoolNameEdit.getText().toString(), getRange(), getRating(), priceEdit.getText().toString());
                Intent intentToListResults = new Intent(MainActivity.this, ListActivity.class);
                intentToListResults.putExtra("searchObject", searchData);
               startActivity(intentToListResults);
            }
        });
    }

    /**
     * Fills the database with preschool objects, but only if there are no
     * current schools in it.  If the cursor comes back with a count of 0,
     * the insert is skipped.
     */
    private void fillDatabaseWithPreschools() {
        // do a find on all preschools
        cursor = searchHelper.getAllPreschools();
        cursor.moveToFirst();

        // if the cursor result is empty, insert the schools below
        // if the cursor isn't empty skip the insert
        if (cursor.getCount() > 0) {
            cursor.close();
        } else {

            ArrayList<PreSchool> preschools = PopulatePreschoolDB.getPreSchoolItems(MainActivity.this);
            for (PreSchool school : preschools) {
                searchHelper.insertIntoPreschools(school.getName(), school.getSchoolDescription(), school.getPrice(),
                        school.getStreetAddress(), school.getCity(), school.getState(), school.getZipCode(),
                        school.getType(), school.getRating(), school.getRegion(), school.getRange(),
                        school.getPhoneNumber(), school.getAgeGroup(), school.getFavorite(), school.getImages(), school.getImageDescription());
            }
            cursor.close();
        }
      }

    /**
     *  This interprets the string value of the selected item in the drop down,
     *  and converts to a single integer value.
     *
     *  Returns  a string value of that integer to the intentToListResults.
     */
    private String getRange() {
        int rangeAsNumber = 0;
        if (rangeSpinner.getSelectedItem().toString().isEmpty()) {
            return "";
        }
        switch (rangeSpinner.getSelectedItem().toString()) {
            case "1 Mile":
                rangeAsNumber = 1;
                break;
            case "5 Miles":
                rangeAsNumber = 5;
                break;
            case "10 Miles":
                rangeAsNumber = 10;
                break;
            case "15 Miles":
                rangeAsNumber = 15;
                break;
            case "20 Miles":
                rangeAsNumber = 20;
                break;
            default:
                break;
       }

        return String.valueOf(rangeAsNumber);
    }

    /**
     *   Takes the int value of the selected item in the drop down,
     *  and returns a string value to be passed in the intentToListResults
     */
    private String getRating() {
        int rating=0;
        // TODO try getselecteditemposition
        if (ratingSpinner.getSelectedItem().equals(R.drawable.pixel)) rating = 0;
        else if (ratingSpinner.getSelectedItem().equals(R.drawable.one_star)) rating = 1;
        else if (ratingSpinner.getSelectedItem().equals(R.drawable.two_stars)) rating = 2;
        else if (ratingSpinner.getSelectedItem().equals(R.drawable.three_stars)) rating = 3;
        else if (ratingSpinner.getSelectedItem().equals(R.drawable.four_stars)) rating = 4;
        else if (ratingSpinner.getSelectedItem().equals(R.drawable.five_stars)) rating = 5;

        return String.valueOf(rating);
    }

    /**
     * fills the action bar, only has a Favorite icon
     * @param menu Menu type variable
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main_activity, menu);


        return true;
    }

    /**
     * Gives the user to the option to view all favorites from teh home screen.
     * once the Favorite icon is pressed, the results are shown in the List activity.
     *
     * @param item MenuItem type variable
     * @return the item that was selected.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_main_find_favorites:
                //go to list activity and find all favorites
                cursor = searchHelper.findFavoritePreschools();
                if (cursor.getCount() > 0) {
                    Intent intentForFavs = new Intent(MainActivity.this, ListActivity.class);
                    intentForFavs.putExtra(FIND_FAVORITE_PRESCHOOLS, FAVORITES_VALUE);
                    startActivity(intentForFavs);
                } else {
                    Toast.makeText(MainActivity.this, "No Favorites To View, Add One From The Details Page", Toast.LENGTH_SHORT).show();
                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}