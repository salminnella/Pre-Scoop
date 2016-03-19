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
import android.widget.TextView;

import com.example.anthony.prescoop.DBHelper.DatabaseHelper;
import com.example.anthony.prescoop.R;
import com.example.anthony.prescoop.adapters.SpinAdapter;

public class MainActivity extends AppCompatActivity {

    public static final String SCHOOL_NAME = "schoolName";

    TextView ratingText;
    TextView rangeText;
    TextView addressText;
    TextView budgetText;
    EditText addressEdit;
    EditText budgetEdit;
    EditText schoolNameEdit;
    Spinner rangeSpinner;
    ArrayAdapter<CharSequence> rangeAdapter;
    Spinner ratingSpinner;
    SpinAdapter ratingAdapter;
    Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setOnClickListeners();

        fillTempPreschool();

    }

    private void initViews() {
        ratingText = (TextView) findViewById(R.id.rating_text_main);
        rangeText = (TextView) findViewById(R.id.range_text_main);
        addressText = (TextView) findViewById(R.id.address_text_main);
        budgetText = (TextView) findViewById(R.id.budget_text);
        addressEdit = (EditText) findViewById(R.id.address_edit_main);
        budgetEdit = (EditText) findViewById(R.id.budget_edit);
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
        ratingAdapter = new SpinAdapter(MainActivity.this, new Integer[]{R.drawable.one_star, R.drawable.two_stars, R.drawable.three_stars, R.drawable.four_stars, R.drawable.five_stars});
        //connect the two
        ratingSpinner.setAdapter(ratingAdapter);
    }

    private void setOnClickListeners() {
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = parseSearchCriteria();
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                intent.putExtra(SCHOOL_NAME, schoolNameEdit.getText().toString());
                startActivity(intent);
            }
        });
    }

    private void fillTempPreschool() {
        DatabaseHelper db = new DatabaseHelper(this);
        // do a get on preschools
        Cursor cursor = db.getPreschools();
        cursor.moveToFirst();

        // if the cursor result is empty, insert the schools below
        // if the cursor isn't empty skip the insert
        if (cursor != null && cursor.getCount() > 0) {
            cursor.close();
        } else {
            db.insertIntoPreschools("Acorn Learning Center", R.string.acorn_description, 0.0, "816 Diablo Rd", "Danville", "CA", "94526", "public", 5, 0, null, 0, null, 0, null, 0, null, 0, null);
            db.insertIntoPreschools("The Quarry Lane School", R.string.quarry_description, 1400.00, "3750 Boulder St.", "Pleasanton", "CA", "94568", "public", 3, 0, null, 0, null, 0, null, 0, null, 0, null);
            db.insertIntoPreschools("San Francisco Montessori Academy", R.string.sf_montesorri_description, 0.00, "1566 32nd Ave.", "San Francisco", "CA", "94122", "private", 5, 0, null, 0, null, 0, null, 0, null, 0, null);
            db.insertIntoPreschools("Sunset Co-op Nursery School", R.string.sunset_co_op_description, 305.00, "4245 Lawton St.", "San Francisco", "CA", "94122", "co-op", 4, 0, null, 0, null, 0, null, 0, null, 0, null);

            cursor.close();
        }
      }

    private String parseSearchCriteria() {
        String query = "SELECT name, street, rating FROM preschools ";
        String schoolNameAsString = schoolNameEdit.getText().toString();
        String rangeAsString = rangeSpinner.getSelectedItem().toString();
        String ratingAsString = ratingSpinner.getSelectedItem().toString();
        String budgetAsString = budgetEdit.getText().toString();

        String[] arr_address = addressEdit.getText().toString().split(" ", 4);
        addressEdit.getText().toString();


        if (!schoolNameAsString.isEmpty()) {
            query = query + "WHERE name LIKE \"%" + schoolNameEdit.getText().toString() + "%\"";
        }
//        else if (!rangeAsString.isEmpty()) {
//            query += rangeSpinner.getSelectedItem().toString();
//        } else if (true) {  //need to split the address into the separate city sate zip etc.
//            // add the address items to the query
//        } else if (!budgetAsString.isEmpty()) {
//            query += budgetAsString;
//        }

        return query;
    }
}
