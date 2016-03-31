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

/**
 * Created by anthony on 3/30/16.
 */
public class SearchCriteriaFragment extends Fragment{
    EditText priceEdit;
    EditText schoolNameEdit;
    Button search;
    Spinner rangeSpinner;
    Spinner ratingSpinner;
    OnSelectedFavoritesListener selectedFavoritesListener;

    public interface OnSelectedFavoritesListener{
        void onSelectedFavorites(Cursor c);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            selectedFavoritesListener = (OnSelectedFavoritesListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + "must implement OnSelectedFavoritesListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_criteria, container, false);

        priceEdit = (EditText) view.findViewById(R.id.price_main_edit);
        schoolNameEdit = (EditText) view.findViewById(R.id.school_name_main_edit);
        search = (Button) view.findViewById(R.id.search_button);

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
}
