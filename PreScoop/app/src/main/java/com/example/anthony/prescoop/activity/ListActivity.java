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

import com.example.anthony.prescoop.DBHelper.DatabaseHelper;
import com.example.anthony.prescoop.R;
import com.example.anthony.prescoop.adapters.DBCursorAdapter;

public class ListActivity extends AppCompatActivity {
    public static final String RECORD_ID = "id"; // passed to the details activity to find the preschool
    private static final int REQUEST_CODE = 42;  // used activityResult, to refresh list from details


    ListView listView;  //holds the results from the search criteria entered in Main
    String query;  //holds the filter text within the actionbar
    Boolean isViewingFavorites = false; // which list set is the user looking at - search results or favs list
    String favs;
    // database items
    DatabaseHelper searchHelper = DatabaseHelper.getInstance(ListActivity.this);
    DBCursorAdapter cursorAdapter;
    Cursor cursor;

    // variables used in the sql searches
    String schoolName;
    String schoolRating;
    String schoolRange;
    String schoolPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        initializeViews();

        receiveIntentFromMain();

        setOnClickListener();

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

        schoolName = intentFromMain.getStringExtra(MainActivity.SEARCH_NAME);
        schoolRange = intentFromMain.getStringExtra(MainActivity.SEARCH_RANGE);
        schoolRating = intentFromMain.getStringExtra(MainActivity.SEARCH_RATING);
        schoolPrice = intentFromMain.getStringExtra(MainActivity.SEARCH_PRICE);
        //Search searchCriteria = intentFromMain.getParcelableExtra("searchObject");
        favs = intentFromMain.getStringExtra(MainActivity.SEARCH_FAVS);

        if (favs.equals(MainActivity.FAVS_KEY)) {
            cursor = searchHelper.findFavoritePreschools();
            isViewingFavorites = true;
            cursorAdapter = new DBCursorAdapter(ListActivity.this, cursor, 0);
            listView.setAdapter(cursorAdapter);
            isViewingFavorites = true;

        } else {
            performDatabaseSearch(schoolName, schoolRange, schoolRating, schoolPrice);
        }
    }

    private void performDatabaseSearch(String name, String range, String rating, String price) {
        if (!range.equals("0") && !rating.equals("0") && !price.equals("")) {
            searchByRangeRatingPrice(range, rating, price);
        } else if (!name.equals("")) {
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

    private void searchByRangeRatingPrice(String range, String rating, String price) {
        cursor = searchHelper.findByRangeRatingPrice(range, rating, price);
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
            query = intent.getStringExtra(SearchManager.QUERY);
            cursor = DatabaseHelper.getInstance(ListActivity.this).filterPreschoolList(query);

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
     * @param menu -- inflates the menuitems
     * @return -- always returns true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_list_activity, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_list_search_item).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_list_find_favorites:
                if (isViewingFavorites) {
                    //returns the full list of schools
                    //searchForSchools(query);
                    if (schoolName != null) {
                        performDatabaseSearch(schoolName, schoolRange, schoolRating, schoolPrice);
                    } else {
                        searchForAllSchools();
                    }
                    isViewingFavorites = false;
                    cursorAdapter.swapCursor(cursor);
                    item.setIcon(R.drawable.ic_favorite_border_black_24dp);
                } else {
                    // finds just the favorite schools
                    cursor = searchHelper.findFavoritePreschools();
                    isViewingFavorites = true;
                    cursorAdapter.swapCursor(cursor);
                    item.setIcon(R.drawable.ic_favorite_red_24dp);
                }
        }
        return super.onOptionsItemSelected(item);
    }

    // http://hmkcode.com/android-menu-handling-click-events-changing-menu-items-at-runtime/
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (isViewingFavorites) {
            menu.getItem(1).setIcon(R.drawable.ic_favorite_red_24dp);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // refresh the list for any new favs
                if (isViewingFavorites) {
                    cursor = searchHelper.findFavoritePreschools();
                    isViewingFavorites = true;
                    cursorAdapter = new DBCursorAdapter(ListActivity.this, cursor, 0);
                    listView.setAdapter(cursorAdapter);

                } else {
                    performDatabaseSearch(schoolName, schoolRange, schoolRating, schoolPrice);
                }
            }
        }
    }
}
