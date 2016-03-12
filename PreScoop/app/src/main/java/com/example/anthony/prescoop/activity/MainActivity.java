package com.example.anthony.prescoop.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.anthony.prescoop.R;
import com.example.anthony.prescoop.SpinAdapter;

public class MainActivity extends AppCompatActivity {

    TextView ratingText;
    TextView rangeText;
    TextView addressText;

    EditText addressEdit;

    Spinner rangeSpinner;
    Spinner ratingSpinner;

    int arr_images[] = { R.drawable.one_star,
            R.drawable.two_stars, R.drawable.three_stars,
            R.drawable.four_stars, R.drawable.five_stars};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        ratingText = (TextView) findViewById(R.id.rating_text_main);
        rangeText = (TextView) findViewById(R.id.range_text_main);
        addressText = (TextView) findViewById(R.id.address_text_main);
        addressEdit = (EditText) findViewById(R.id.address_edit_main);
        rangeSpinner = (Spinner) findViewById(R.id.range_spinner_main);
        ratingSpinner = (Spinner) findViewById(R.id.rating_spinner_main);

        // Create an ArrayAdapter using the string-array in strings.xml
        ArrayAdapter<CharSequence> spinAdapter = ArrayAdapter.createFromResource(this, R.array.range_array, android.R.layout.simple_spinner_item);
        // layout to use when the list of choices appears
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //connect the two
        rangeSpinner.setAdapter(spinAdapter);

        ratingSpinner.setAdapter(new SpinAdapter(MainActivity.this, R.layout.spinner_row));
    }

}
