package com.example.anthony.prescoop.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anthony.prescoop.DBHelper.DatabaseHelper;
import com.example.anthony.prescoop.R;

public class DetailActivity extends AppCompatActivity {
    int id;
    ImageView defaultSchoolImage;
    TextView schoolAddress;
    ImageView schoolRating;
    TextView schoolDescription;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true); //enables the up/back button in actionbar

        // get the database instance
        DatabaseHelper helper = DatabaseHelper.getInstance(DetailActivity.this);
        // get the id from the clicked item in ListActivity
        id = getIntent().getIntExtra(ListActivity.RECORD_ID, -1);

        initViews();
    }

    private void initViews() {

    }


    // filling toolbar with menu options, and setting the actions for them
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }
}
