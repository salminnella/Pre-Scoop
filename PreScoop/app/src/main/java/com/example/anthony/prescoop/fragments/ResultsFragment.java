package com.example.anthony.prescoop.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.anthony.prescoop.DBHelper.DatabaseHelper;
import com.example.anthony.prescoop.R;
import com.example.anthony.prescoop.activity.MainActivity;
import com.example.anthony.prescoop.adapters.DBCursorAdapter;
import com.example.anthony.prescoop.models.SearchDataForDB;

/**
 * Created by anthony on 3/30/16.
 */
public class ResultsFragment extends ListFragment {
    private boolean isViewingFavorites = false;
    private Cursor cursor;
    private DatabaseHelper searchHelper;
    private DBCursorAdapter cursorAdapter;
    private SearchDataForDB searchData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findFavorites();
        searchDatabaseForSchools(searchData);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        searchHelper = DatabaseHelper.getInstance(getActivity());
        //cursor = searchHelper.findFavoritePreschools();

        Bundle bundle = getArguments();
        if (bundle != null) {
            searchData = bundle.getParcelable(MainActivity.SEARCH_CRITERIA_VALUES);
            cursor = searchHelper.prepareSearchQuery(searchData);
        } else {
            cursor = searchHelper.findFavoritePreschools();
        }

        DBCursorAdapter cursorAdapter = new DBCursorAdapter(getActivity(), cursor, 0);
        setListAdapter(cursorAdapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void findFavorites() {
        if (true) {
            searchHelper = DatabaseHelper.getInstance(getActivity());
            cursor = searchHelper.findFavoritePreschools();
            cursorAdapter = new DBCursorAdapter(getActivity(), cursor, 0);
            setListAdapter(cursorAdapter);
        } else {
            Toast.makeText(getActivity(), R.string.no_results, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Uses the chosen criteria, or favorites option, to search the database for preschools
     * Returns the search results or all of the favorite schools
     */
    private void searchDatabaseForSchools(SearchDataForDB searchDataForDB) {

        if (isViewingFavorites) {
            cursor = searchHelper.findFavoritePreschools();
        } else if (searchDataForDB != null){
            cursor = searchHelper.prepareSearchQuery(searchDataForDB);
        }

        if (cursor.getCount() > 0) {
            cursorAdapter = new DBCursorAdapter(getActivity(), cursor, 0);
            setListAdapter(cursorAdapter);
        } else {
            Toast.makeText(getActivity(), R.string.no_results, Toast.LENGTH_SHORT).show();
        }
    }
}
