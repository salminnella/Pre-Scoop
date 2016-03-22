package com.example.anthony.prescoop.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.anthony.prescoop.DBHelper.DatabaseHelper;
import com.example.anthony.prescoop.R;
import com.example.anthony.prescoop.adapters.SpinAdapter;

public class MainActivity extends AppCompatActivity {

    public static final String SEARCH_CRITERIA = "searchCriteria";
    public static final String SEARCH_NAME = "searchName";
    public static final String SEARCH_RANGE = "searchRange";
    public static final String SEARCH_RATING = "searchRating";
    public static final String SEARCH_PRICE = "searchPrice";

    EditText addressEdit;
    EditText priceEdit;
    EditText schoolNameEdit;
    Spinner rangeSpinner;
    ArrayAdapter<CharSequence> rangeAdapter;
    Spinner ratingSpinner;
    SpinAdapter ratingAdapter;
    Button search;


    String schoolNameAsString;
    String rangeAsString;
    String ratingAsString;
    String budgetAsString;
    String[] arr_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        setOnClickListeners();

        fillTempPreschool();

    }

    private void initializeViews() {
        addressEdit = (EditText) findViewById(R.id.address_main_edit);
        priceEdit = (EditText) findViewById(R.id.price_main_edit);
        schoolNameEdit = (EditText) findViewById(R.id.school_name_main_edit);
        search = (Button) findViewById(R.id.search_button);



        rangeSpinner = (Spinner) findViewById(R.id.range_spinner_main);
        // Create an ArrayAdapter for Range drop down using the string-array in strings.xml
        rangeAdapter = ArrayAdapter.createFromResource(this, R.array.range_array, android.R.layout.simple_spinner_item);
        // default layout to use when the list of choices appears
        rangeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //connect the two
        rangeSpinner.setAdapter(rangeAdapter);

        // for the images on the rating drop down spinner
        ratingSpinner = (Spinner) findViewById(R.id.rating_spinner_main);
        // Create a custom adapter for rating drop down
        ratingAdapter = new SpinAdapter(MainActivity.this, new Integer[]{R.drawable.pixel, R.drawable.one_star, R.drawable.two_stars, R.drawable.three_stars, R.drawable.four_stars, R.drawable.five_stars});
        //connect the two
        ratingSpinner.setAdapter(ratingAdapter);


    }

    private void setOnClickListeners() {
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = parseSearchCriteria();
                String name = nameSearch();
                String range = String.valueOf(appendRangeToQuery());
                String rating = String.valueOf(ratingSearch());

                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                intent.putExtra(SEARCH_CRITERIA, searchQuery);
                intent.putExtra(SEARCH_NAME, name);
                intent.putExtra(SEARCH_RANGE, range);
                intent.putExtra(SEARCH_RATING, rating);
                intent.putExtra(SEARCH_PRICE, priceEdit.getText().toString());
                startActivity(intent);
            }
        });
    }

    private void fillTempPreschool() {
        DatabaseHelper db = new DatabaseHelper(this);
        // do a get on preschools
        Cursor cursor = db.getAllPreschools();
        cursor.moveToFirst();

        // if the cursor result is empty, insert the schools below
        // if the cursor isn't empty skip the insert
        if (cursor != null && cursor.getCount() > 0) {
            cursor.close();
        } else {
            db.insertIntoPreschools("Acorn Learning Center", R.string.acorn_description, 800.00, "816 Diablo Rd", "Danville", "CA", "94526", "public", 4, "East Bay", 16, "925.837.1145", "2-5 years", R.drawable.acorn_main_photo, null, R.drawable.acorn_photo_two, null, 0, null, 0, null, 0, null, 0);
            db.insertIntoPreschools("The Quarry Lane School", R.string.quarry_description, 1400.00, "3750 Boulder St.", "Pleasanton", "CA", "94568", "public", 3, "Easy Bay", 11, "Phone Number", "Age Group", 0, null, 0, null, 0, null, 0, null, 0, null, 0);
            db.insertIntoPreschools("San Francisco Montessori Academy", R.string.sf_montesorri_description, 0.00, "1566 32nd Ave.", "San Francisco", "CA", "94122", "private", 5, "San Francisco", 6, "Phone Number", "Age Group", 0, null, 0, null, 0, null, 0, null, 0, null, 0);
            db.insertIntoPreschools("Sunset Co-op Nursery School", R.string.sunset_co_op_description, 305.00, "4245 Lawton St.", "San Francisco", "CA", "94122", "co-op", 4, "San Francisco", 4, "Phone Number", "Age Group", 0, null, 0, null, 0, null, 0, null, 0, null, 0);

            cursor.close();
        }
      }

    private String parseSearchCriteria() {
        String query = "SELECT * FROM preschools ";

        schoolNameAsString = schoolNameEdit.getText().toString();
        rangeAsString = rangeSpinner.getSelectedItem().toString();
        arr_address = addressEdit.getText().toString().split(" ", 4);
        addressEdit.getText().toString();
        ratingAsString = ratingSpinner.getSelectedItem().toString();
        budgetAsString = priceEdit.getText().toString();
        ratingAsString =  String.valueOf(ratingSearch());

        if (!schoolNameAsString.isEmpty()) {
            query += "WHERE name LIKE \"%" + schoolNameAsString + "%\"";
        }
        else if (!rangeAsString.equals("Select A Range")) {
            query += addRangeToQuery();
        } else if (!ratingAsString.equals("0")) {
            query += ratingSpinner.getSelectedItem().toString();
        } else if (!budgetAsString.isEmpty()) {
            query += budgetAsString;
        }

        return query;
    }

    public String nameSearch() {
        if (schoolNameEdit.getText().toString().isEmpty()) {
            return "";
        }
        return schoolNameEdit.getText().toString();
    }

    private String addRangeToQuery() {
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


        if (schoolNameEdit.getText().toString().isEmpty()) {
            return " WHERE range <= " + String.valueOf(rangeAsNumber);
        } else {
            return " AND range <= " + String.valueOf(rangeAsNumber);
        }


    }
    private int appendRangeToQuery() {
        int rangeAsNumber = 0;
        if (rangeSpinner.getSelectedItem().toString().isEmpty()) {
            return 0;
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

        return rangeAsNumber;
    }

    private int ratingSearch() {
        int rating=0;
//        switch (ratingSpinner.getSelectedItem()) {
//            case ratingSpinner.getSelectedItem().equals(R.drawable.pixel): rating = 0;
//                break;
//            case R.drawable.one_star: rating = 1;
//                break;
//            case R.drawable.two_stars: rating = 2;
//                break;
//            case R.drawable.three_stars: rating = 3;
//                break;
//            case R.drawable.four_stars: rating = 4;
//                break;
//            case R.drawable.five_stars: rating = 5;
//                break;
//            default: rating = 0;
//                break;
//        }
//        return rating;
        // TODO try getselecteditemposition
        if (ratingSpinner.getSelectedItem().equals(R.drawable.pixel)) rating = 0;
        else if (ratingSpinner.getSelectedItem().equals(R.drawable.one_star)) rating = 1;
        else if (ratingSpinner.getSelectedItem().equals(R.drawable.two_stars)) rating = 2;
        else if (ratingSpinner.getSelectedItem().equals(R.drawable.three_stars)) rating = 3;
        else if (ratingSpinner.getSelectedItem().equals(R.drawable.four_stars)) rating = 4;
        else if (ratingSpinner.getSelectedItem().equals(R.drawable.five_stars)) rating = 5;

        return rating;

    }
}
