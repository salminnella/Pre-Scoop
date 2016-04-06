package com.example.anthony.prescoop.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.anthony.prescoop.R;
import com.example.anthony.prescoop.adapters.SpinAdapter;
import com.example.anthony.prescoop.models.SearchDataForDB;

/**
 * Created by anthony on 3/30/16.
 */
public class SearchCriteriaFragment extends Fragment{
    private EditText priceEdit;
    private EditText schoolNameEdit;
    private Button searchButton;
    private Spinner rangeSpinner;
    private Spinner ratingSpinner;
    OnSelectedListener selectedListener;

    public interface OnSelectedListener {
        void onSelectedFavorites(Cursor c);

        void onSelectedSearch(SearchDataForDB searchDataForDB);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            selectedListener = (OnSelectedListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + "must implement OnSelectedListener");
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_criteria, container, false);

        priceEdit = (EditText) view.findViewById(R.id.price_main_edit);
        schoolNameEdit = (EditText) view.findViewById(R.id.school_name_main_edit);
        searchButton = (Button) view.findViewById(R.id.search_button);

        rangeSpinner = (Spinner) view.findViewById(R.id.range_spinner_main);
        ArrayAdapter<CharSequence> rangeAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.range_array, android.R.layout.simple_spinner_item);
        rangeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rangeSpinner.setAdapter(rangeAdapter);

        // for the rating spinner, filled with images to select a minimum rating ( 1 star, 2 stars etc.)
        ratingSpinner = (Spinner) view.findViewById(R.id.rating_spinner_main);
        SpinAdapter ratingAdapter = new SpinAdapter(getActivity(), new Integer[]{R.drawable.pixel, R.drawable.one_star, R.drawable.two_stars, R.drawable.three_stars, R.drawable.four_stars, R.drawable.five_stars});
        ratingSpinner.setAdapter(ratingAdapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchDataForDB searchData = new SearchDataForDB(schoolNameEdit.getText().toString(), getRange(), getRating(), priceEdit.getText().toString());
                selectedListener.onSelectedSearch(searchData);
            }
        });
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
        if (ratingSpinner.getSelectedItem().equals(R.drawable.pixel)) rating = 0;
        else if (ratingSpinner.getSelectedItem().equals(R.drawable.one_star)) rating = 1;
        else if (ratingSpinner.getSelectedItem().equals(R.drawable.two_stars)) rating = 2;
        else if (ratingSpinner.getSelectedItem().equals(R.drawable.three_stars)) rating = 3;
        else if (ratingSpinner.getSelectedItem().equals(R.drawable.four_stars)) rating = 4;
        else if (ratingSpinner.getSelectedItem().equals(R.drawable.five_stars)) rating = 5;

        return String.valueOf(rating);
    }
}
