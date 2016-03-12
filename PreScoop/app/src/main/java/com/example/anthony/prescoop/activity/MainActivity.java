package com.example.anthony.prescoop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.anthony.prescoop.R;
import com.example.anthony.prescoop.adapters.SpinAdapter;
import com.example.anthony.prescoop.models.Rating;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView ratingText;
    TextView rangeText;
    TextView addressText;
    TextView budgetText;

    EditText addressEdit;
    EditText budgetEdit;

    Spinner rangeSpinner;
    Spinner ratingSpinner;

    Button search;
    ArrayList<Rating> ratingSpinnerArray;
    SpinAdapter spinAdapter;



    int arr_images[] = { R.drawable.one_star,
            R.drawable.two_stars, R.drawable.three_stars,
            R.drawable.four_stars, R.drawable.five_stars};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setOnClickListeners();

    }

    private void initViews() {
        ratingText = (TextView) findViewById(R.id.rating_text_main);
        rangeText = (TextView) findViewById(R.id.range_text_main);
        addressText = (TextView) findViewById(R.id.address_text_main);
        budgetText = (TextView) findViewById(R.id.budget_text);
        addressEdit = (EditText) findViewById(R.id.address_edit_main);
        budgetEdit = (EditText) findViewById(R.id.budget_edit);
        rangeSpinner = (Spinner) findViewById(R.id.range_spinner_main);
        ratingSpinner = (Spinner) findViewById(R.id.rating_spinner_main);
        search = (Button) findViewById(R.id.search_button);

        // Create an ArrayAdapter using the string-array in strings.xml
        ArrayAdapter<CharSequence> spinAdapter = ArrayAdapter.createFromResource(this, R.array.range_array, android.R.layout.simple_spinner_item);
        // layout to use when the list of choices appears
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //connect the two
        rangeSpinner.setAdapter(spinAdapter);

        ratingSpinner.setAdapter(new SpinAdapter(MainActivity.this, R.layout.spinner_row));
    }

    private void setOnClickListeners() {
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });
    }



}
