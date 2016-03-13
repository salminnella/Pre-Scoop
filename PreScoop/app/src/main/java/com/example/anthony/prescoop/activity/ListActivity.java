package com.example.anthony.prescoop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.anthony.prescoop.R;
import com.example.anthony.prescoop.adapters.SchoolAdapter;
import com.example.anthony.prescoop.models.PreSchool;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {


    ArrayList<PreSchool> preSchools;
    ListView listView;
    SchoolAdapter schoolAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        // implements custom toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.preschool_toolbar);
        setSupportActionBar(toolbar);
        // enabling the up/back button the toolbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);



        fillListOfPreschools();
        initViews();
        setOnClickListener();

    }

    /**
     * filling defualt data for list of preschools
     */
    private void fillListOfPreschools() {
        preSchools = new ArrayList<>();
        preSchools.add(new PreSchool("Acorn Learning Center", "816 Diablo Rd, Danville, CA 94526", 0.00, R.string.acorn_description, 5, "East Bay"));
        preSchools.add(new PreSchool("The Quarry Lane School", "3750 Boulder St., Pleasanton", 1400.00, R.string.quarry_description, 3, "East Bay"));
        preSchools.add(new PreSchool("San Francisco Montessori Academy", "1566 32nd Ave., San Francisco", 0.00, R.string.sf_montesorri_description, 5, "San Francisco"));
        preSchools.add(new PreSchool("Sunset Co-Op Nursery School", "4245 Lawton St, San Francisco, CA", 305.00, R.string.sunset_co_op_description, 4, "San Francisco"));
        preSchools.add(new PreSchool("Little Urbanites", "1258 20th Ave San Francisco California 94122", 0.00, R.string.little_urbanites, 4, "San Francisco"));
    }

    // trying to figure out how to add images to the preshool objects int the image array
    private void addImages() {
        //preSchools.add();
    }

    /**
     * initializing all views in activity
     */
    private void initViews() {
        listView = (ListView) findViewById(R.id.school_results_list);
        schoolAdapter = new SchoolAdapter(ListActivity.this, preSchools);
        listView.setAdapter(schoolAdapter);
    }

    /**
     * setting onclick listener for item click in activity
     */
    private void setOnClickListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * filling toolbar with menu options, and setting the actions for them
     * http://developer.android.com/training/appbar/action-views.html
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list_activity, menu);

        // action view when user clicks on filter icon
        MenuItem searchItem = menu.findItem(R.id.action_filter);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        // Define the listener
        MenuItemCompat.OnActionExpandListener expandListener = new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Do something when action item collapses
                return true;  // Return true to collapse action view
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Do something when expanded
                return true;  // Return true to expand action view
            }
        };

        // Get the MenuItem for the action item
        MenuItem actionMenuItem = menu.findItem(R.id.action_filter);

        // Assign the listener to that action item
        MenuItemCompat.setOnActionExpandListener(actionMenuItem, expandListener);

        // Any other things you have to do when creating the options menu…

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
