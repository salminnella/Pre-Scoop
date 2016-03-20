package com.example.anthony.prescoop.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.anthony.prescoop.DBHelper.DatabaseHelper;
import com.example.anthony.prescoop.R;
import com.example.anthony.prescoop.adapters.DBCursorAdapter;

public class ListActivity extends AppCompatActivity {
    public static final String RECORD_ID = "id";

    ListView listView;
    DBCursorAdapter cursorAdapter;
    String query;
    Cursor cursor;  // TODO: this has not been closed properly

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        initializeViews();

        //receive the search criteria from main, and performs the search
        receiveIntentFromMain();

        setOnClickListener(cursor);

        handleIntent(getIntent());
    }

    /**
     * initializing all views in activity
     */
    private void initializeViews() {
        listView = (ListView) findViewById(R.id.school_results_list);
    }

    /**
     * receives the search criteria from main activity
     */
    private void receiveIntentFromMain() {
        Intent intentFromMain = getIntent();
        if (intentFromMain == null) {
            return;
        }
        query = intentFromMain.getStringExtra(MainActivity.SCHOOL_NAME);

        searchForSchools(query);
    }

    private void searchForSchools(String query) {
        DatabaseHelper searchHelper = DatabaseHelper.getInstance(ListActivity.this);

        if (!query.isEmpty()) {
            cursor = searchHelper.searchPreschools(query);
        } else {
            cursor = searchHelper.getAllPreschools();
        }

        //listview connects to the adapter
        cursorAdapter = new DBCursorAdapter(ListActivity.this, cursor, 0);
        listView.setAdapter(cursorAdapter);
    }


    /**
     * Takes the filter string from the action bar and passes it to handleIntent to perform a new
     * search from the database.
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    /**
     * Filters the search result list
     * @param intent
     */
    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Cursor cursor = DatabaseHelper.getInstance(ListActivity.this).searchPreschools(query);

            listView = (ListView) findViewById(R.id.school_results_list);

            if (cursorAdapter == null) {
                cursorAdapter = new DBCursorAdapter(ListActivity.this, cursor, 0);
                listView.setAdapter(cursorAdapter);
            } else {
                cursorAdapter.changeCursor(cursor);
            }
        }
    }

    /**
     * setting listener for list item click
     */
    private void setOnClickListener(final Cursor cursor) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this, DetailActivity.class);
                cursor.moveToPosition(position);
                intent.putExtra(RECORD_ID, cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ID)));
                startActivity(intent);
            }
        });
    }


    /**
     * filling toolbar with menu options, and setting the actions for them
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_list_activity, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search_item).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }
}
