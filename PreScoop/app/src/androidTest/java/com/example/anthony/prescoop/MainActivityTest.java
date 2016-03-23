package com.example.anthony.prescoop;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.anthony.prescoop.activity.MainActivity;
import com.example.anthony.prescoop.adapters.SpinAdapter;

/**
 * Created by anthony on 3/23/16.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
    MainActivity mainActivity;

    EditText priceEdit;
    EditText schoolNameEdit;
    Spinner rangeSpinner;
    ArrayAdapter<CharSequence> rangeAdapter;
    Spinner ratingSpinner;
    SpinAdapter ratingAdapter;
    Button search;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        mainActivity = getActivity();

        schoolNameEdit = (EditText) mainActivity.findViewById(R.id.school_name_main_edit);
        rangeSpinner = (Spinner) mainActivity.findViewById(R.id.range_spinner_main);
        rangeAdapter = ArrayAdapter.createFromResource(mainActivity, R.array.range_array, android.R.layout.simple_spinner_item);
        ratingSpinner = (Spinner) mainActivity.findViewById(R.id.rating_spinner_main);
        ratingAdapter = new SpinAdapter(mainActivity, new Integer[]{R.drawable.pixel, R.drawable.one_star, R.drawable.two_stars, R.drawable.three_stars, R.drawable.four_stars, R.drawable.five_stars});
        priceEdit = (EditText) mainActivity.findViewById(R.id.price_main_edit);
        search = (Button) mainActivity.findViewById(R.id.search_button);

    }

    // confirming views are initialized
    @MediumTest
    public void testPreconditions() {
        assertNotNull("activity is null", mainActivity);
        assertNotNull("school name is null", schoolNameEdit);
        assertNotNull("range spinner is null", rangeSpinner);
        assertNotNull("rating spinner is null", ratingSpinner);
        assertNotNull("price is null", priceEdit);
        assertNotNull("search button is null", search);
    }

    @MediumTest
    public void testEditTextViews() {

        schoolNameEdit.setText("acorn");
        assertEquals("acorn", schoolNameEdit.getText());
    }
}
