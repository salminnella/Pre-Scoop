package com.example.anthony.prescoop.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.anthony.prescoop.R;
import com.example.anthony.prescoop.adapters.DBCursorAdapter;

/**
 * Created by anthony on 3/30/16.
 */
public class FavoritesFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        DBCursorAdapter cursorAdapter = new DBCursorAdapter(getActivity(), cursor, 0);
        setListAdapter(cursorAdapter);
        return view;
    }

    public void findFavorites(Cursor cursor) {
        Log.i("FavFragment", "findfavorites - cursor size is: " + cursor.getCount());

        //ListView listView = (ListView) getView().findViewById(R.id.school_results_list);
        if (cursor.getCount() > 0) {
            DBCursorAdapter cursorAdapter = new DBCursorAdapter(getActivity(), cursor, 0);
            //Log.i("FavFragment", "listView: " + listView);
            Log.i("FavFragment", "cursorAdapter: " + cursorAdapter);
            //listView.setAdapter(cursorAdapter);
            //setListAdapter(cursorAdapter);
        } else {
            Toast.makeText(getActivity(), R.string.no_results, Toast.LENGTH_SHORT).show();
        }

    }
}
