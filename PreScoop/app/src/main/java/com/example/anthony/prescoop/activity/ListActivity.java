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
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.anthony.prescoop.DBHelper.DatabaseHelper;
import com.example.anthony.prescoop.R;
import com.example.anthony.prescoop.adapters.DBCursorAdapter;

public class ListActivity extends AppCompatActivity {
    public static final String RECORD_ID = "id";
    public static final String QUERY = "searchQuery";
    private static final int REQUEST_CODE = 42;
    private static final int ERROR_CODE = -1;

    ListView listView;
    DBCursorAdapter cursorAdapter;
    String query;
    Cursor cursor;  // TODO: this has not been closed properly
    Boolean isViewingFavorites = false;
    TextView vewingFavsText;
    DatabaseHelper searchHelper = DatabaseHelper.getInstance(ListActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        initializeViews();

        //receive the search criteria from main, and performs the search
        receiveIntentFromMain();

        setOnClickListener();

        handleIntent(getIntent());
    }

    /**
     * initializing all views in activity
     */
    private void initializeViews() {
        listView = (ListView) findViewById(R.id.school_results_list);
        vewingFavsText = (TextView) findViewById(R.id.favs_are_on_list_text);
    }

    /**
     * receives the search criteria from main activity
     */
    private void receiveIntentFromMain() {
        Intent intentFromMain = getIntent();
        if (intentFromMain == null) {
            return;
        }
        query = intentFromMain.getStringExtra(MainActivity.SEARCH_CRITERIA);
        String name = intentFromMain.getStringExtra(MainActivity.SEARCH_NAME);
        String range = String.valueOf(intentFromMain.getIntExtra(MainActivity.SEARCH_RANGE, ERROR_CODE));
        String rating = intentFromMain.getStringExtra(MainActivity.SEARCH_RATING);
        String price = intentFromMain.getStringExtra(MainActivity.SEARCH_PRICE);

        //searchForSchools(query);
        //searchingForSchools(name, range, rating, price);

        if (name != null) {
            searchByName(name);
        } else if (!range.equals("0")) {
            searchByRange(range);
        } else if (!rating.equals("0")) {
            searchByRating(rating);
        } else if (!price.equals("")) {
            searchByPrice(price);
        } else {
            searchForAllSchools();
        }
    }

    private void searchByName(String name) {
        cursor = searchHelper.findPreschoolsByName(name);
        //listview connects to the adapter
        if (cursor != null) {
            cursorAdapter = new DBCursorAdapter(ListActivity.this, cursor, 0);
            listView.setAdapter(cursorAdapter);
        }
    }

    private void searchByRange(String range) {
        cursor = searchHelper.findPreschoolsByRange(range);
        //listview connects to the adapter
        if (cursor != null) {
            cursorAdapter = new DBCursorAdapter(ListActivity.this, cursor, 0);
            listView.setAdapter(cursorAdapter);
        }
    }

    private void searchByRating(String rating) {
        cursor = searchHelper.findPreschoolsByRating(rating);
        //listview connects to the adapter
        if (cursor != null) {
            cursorAdapter = new DBCursorAdapter(ListActivity.this, cursor, 0);
            listView.setAdapter(cursorAdapter);
        }
    }

    private void searchByPrice(String price) {
        cursor = searchHelper.findPreschoolsByPrice(price);
        //listview connects to the adapter
        if (cursor != null) {
            cursorAdapter = new DBCursorAdapter(ListActivity.this, cursor, 0);
            listView.setAdapter(cursorAdapter);
        }
    }

    private void searchForAllSchools() {
        cursor = searchHelper.getAllPreschools();
        //listview connects to the adapter
        if (cursor != null) {
            cursorAdapter = new DBCursorAdapter(ListActivity.this, cursor, 0);
            listView.setAdapter(cursorAdapter);
        }
    }

    private void searchForSchools(String query) {
        if (!query.isEmpty()) {
            cursor = searchHelper.searchPreschoolsRawQuery("select * from preschools Where name LIKE \"\" or range <= 15 AND rating >= 3");
        } else {
            cursor = searchHelper.getAllPreschools();
        }

        //listview connects to the adapter
        cursorAdapter = new DBCursorAdapter(ListActivity.this, cursor, 0);
        listView.setAdapter(cursorAdapter);
    }

    private void searchingForSchools(String name, String range, String rating, String price) {
        if (rating.equals("0")) {
            rating = "";
        }

        cursor = searchHelper.searchPreschools(name, range, rating, price);

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
            //cursor = DatabaseHelper.getInstance(ListActivity.this).searchPreschools(query);

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
    private void setOnClickListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this, DetailActivity.class);
                cursor.moveToPosition(position);
                intent.putExtra(RECORD_ID, cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ID)));
                startActivityForResult(intent, REQUEST_CODE);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_find_favorites:
                if (isViewingFavorites) {
                    //returns the full list of schools
                    searchForSchools(query);
                    isViewingFavorites = false;
                    vewingFavsText.setVisibility(TextView.INVISIBLE);

                    cursorAdapter.swapCursor(cursor);
                } else {
                    // finds just the favorite schools
                    cursor = searchHelper.findFavoritePreschools();
                    isViewingFavorites = true;
                    vewingFavsText.setVisibility(TextView.VISIBLE);
                    cursorAdapter.swapCursor(cursor);
                }


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                searchForSchools(query);
                //cursorAdapter.swapCursor(cursor);
            }
        }
    }
}
