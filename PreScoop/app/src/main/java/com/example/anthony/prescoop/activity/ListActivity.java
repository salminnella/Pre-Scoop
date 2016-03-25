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
import android.widget.Toast;

import com.example.anthony.prescoop.DBHelper.DatabaseHelper;
import com.example.anthony.prescoop.R;
import com.example.anthony.prescoop.adapters.DBCursorAdapter;
import com.example.anthony.prescoop.models.SearchDataForDB;


/**
 *  Performs a find in the database using the entered search results from the MainActivity,
 *  and displays the results in a list.
 *
 *  Also displays the results of the user chosen Favorite Preschools
 */
public class ListActivity extends AppCompatActivity {
    public static final String RECORD_ID = "id"; // passed to the details activity to find the preschool
    private static final int REQUEST_CODE = 42;  // used onActivityResult to refresh list from DetailActivity


    ListView listView;  //holds the results from the search criteria entered in Main
    String query;  //holds the filter string from within the actionbar
    Boolean isViewingFavorites = false; // which list set is the user looking at - search results or favorites

    // database items
    DatabaseHelper searchHelper;
    DBCursorAdapter cursorAdapter;
    Cursor cursor;

    // variables used in the sql searches
    String schoolName;
    String schoolRating;
    String schoolRange;
    String schoolPrice;

    // holds all the search criteria from main activity
    SearchDataForDB searchData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        searchHelper = DatabaseHelper.getInstance(ListActivity.this);

        initializeViews();

        receiveIntentFromMain();

        searchDatabaseForSchools();

        setOnItemClickListener();

        handleIntent(getIntent());
    }

    /**
     * initializing all views in activity
     */
    private void initializeViews() {
        listView = (ListView) findViewById(R.id.school_results_list);
    }

    /**
     * receives chosen search criteria from main activity
     */
    private void receiveIntentFromMain() {
        Intent intentFromMain = getIntent();
        if (intentFromMain == null) {
            return;
        }

        schoolName = intentFromMain.getStringExtra(MainActivity.SEARCH_PRESCHOOL_NAME);
        schoolRange = intentFromMain.getStringExtra(MainActivity.SEARCH_PRESCHOOL_RANGE);
        schoolRating = intentFromMain.getStringExtra(MainActivity.SEARCH_PRESCHOOL_RATING);
        schoolPrice = intentFromMain.getStringExtra(MainActivity.SEARCH_PRESCHOOL_PRICE);
        //searchData = intentFromMain.getParcelableExtra("searchObject");
        //Log.i("list act", "search data is " + searchData.getSearchCriteria()[0]);
        isViewingFavorites = intentFromMain.getBooleanExtra(MainActivity.FIND_FAVORITE_PRESCHOOLS, false);
    }

    /**
     * setting listener for list item click
     */
    private void setOnItemClickListener() {
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
     * Uses the chosen criteria, or favorites option, to search the database for preschools
     * Returns the search results or all of the favorite schools
     */
    private void searchDatabaseForSchools() {
        if (isViewingFavorites) {
            cursor = searchHelper.findFavoritePreschools();
        } else {
            //searchHelper.searchDB(searchData);
            search(schoolName, schoolRange, schoolRating, schoolPrice);
        }

        if (cursor.getCount() > 0) {
            cursorAdapter = new DBCursorAdapter(ListActivity.this, cursor, 0);
            listView.setAdapter(cursorAdapter);
        }
    }

    /**
     * Performs different searches based on the selected criteria.
     * @param name  String
     * @param range  String
     * @param rating Rating
     * @param price String
     */
    private void search(String name, String range, String rating, String price) {
        if (!range.equals("0") && !rating.equals("0") && !price.equals("")) {
            cursor = searchHelper.findByRangeRatingPrice(range, rating, price);
        } else if (!name.equals("")) {
            cursor = searchHelper.findPreschoolsByName(name);
        } else if (!range.equals("0")) {
            cursor = searchHelper.findPreschoolsByRange(range);
        } else if (!rating.equals("0")) {
            cursor = searchHelper.findPreschoolsByRating(rating);
        } else if (!price.equals("")) {
            cursor = searchHelper.findPreschoolsByPrice(price);
        } else {
            cursor = searchHelper.getAllPreschools();
        }
    }

    /**
     * Takes the filter string from the action bar and passes it to handleIntent to perform a new
     * search from the database.
     * @param intent Intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    /**
     * Filters the search result list
     * @param intent Intent
     */
    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            query = intent.getStringExtra(SearchManager.QUERY);
            cursor = searchHelper.filterPreschoolList(query);
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
     * filling actionbar with menu options, and setting the actions for them
     *
     * @param menu Menu
     * @return -- boolean
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

    /**
     * menu options for search filter, and view favorites.
     * Favorites icon will toggle between finding favorites,
     * and user chosen search criteria
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_list_find_favorites:
                if (isViewingFavorites) {
                    if (schoolName != null) {
                        search(schoolName, schoolRange, schoolRating, schoolPrice);
                    } else {
                        cursor = searchHelper.getAllPreschools();
                    }

                    isViewingFavorites = false;
                    cursorAdapter.changeCursor(cursor);
                    item.setIcon(R.drawable.ic_favorite_border_black_24dp);
                } else {
                    // finds just the favorite schools
                    cursor = searchHelper.findFavoritePreschools();
                    if (cursor.getCount() == 0) {
                        Toast.makeText(ListActivity.this, "No Favorites To View, Add One From The Details Page", Toast.LENGTH_SHORT).show();
                    }
                    isViewingFavorites = true;
                    cursorAdapter.changeCursor(cursor);
                    item.setIcon(R.drawable.ic_favorite_red_24dp);
                }
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * The favorites icon is set to use the un-filled image of the heart.
     * When selecting to view all favorites from the home screen, the
     * heart in the menu will be switched to the red one.
     * @param menu Menu
     * @return Boolean
     */
    // http://hmkcode.com/android-menu-handling-click-events-changing-menu-items-at-runtime/
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (isViewingFavorites) {
            menu.getItem(1).setIcon(R.drawable.ic_favorite_red_24dp);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * When coming back from the DetailActivity, the list will be refreshed
     * for any new favorites.
     *
     * @param requestCode  Int key
     * @param resultCode  int RESULT_OK
     * @param data Intent
     */
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
                    search(schoolName, schoolRange, schoolRating, schoolPrice);
                    cursorAdapter = new DBCursorAdapter(ListActivity.this, cursor, 0);
                    listView.setAdapter(cursorAdapter);
                }
            }
        }
    }
}
