package com.example.anthony.prescoop.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by anthony on 3/15/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "PreSchools";

    // columns
    public static final String COL_ID = "_id";
    public static final String COL_NAME = "name";
    public static final String COL_DESCRIPTION = "description";
    public static final String COL_MONTHLY_PRICE = "price";
    public static final String COL_STREET_ADDRESS = "streetAddress";
    public static final String COL_CITY = "city";
    public static final String COL_STATE = "state";
    public static final String COL_ZIP = "zipCode";
    public static final String COL_TYPE = "type";
    public static final String COL_RATING = "rating";
    public static final String COL_PHOTO_1 = "photo1";
    public static final String COL_PHOTO_1_DESCRIPTION = "photo1Desc";
    public static final String COL_PHOTO_2 = "photo2";
    public static final String COL_PHOTO_2_DESCRIPTION = "photo2Desc";
    public static final String COL_PHOTO_3 = "photo3";
    public static final String COL_PHOTO_3_DESCRIPTION = "photo3Desc";
    public static final String COL_PHOTO_4 = "photo4";
    public static final String COL_PHOTO_4_DESCRIPTION = "photo4Desc";
    public static final String COL_PHOTO_5 = "photo5";
    public static final String COL_PHOTO_5_DESCRIPTION = "photo5Desc";

    // builds all columns in one array for queries later on
    public static final String[] COLUMNS = {COL_ID, COL_NAME, COL_DESCRIPTION, COL_MONTHLY_PRICE,
            COL_STREET_ADDRESS, COL_CITY, COL_STATE, COL_ZIP, COL_TYPE, COL_RATING, COL_PHOTO_1,
            COL_PHOTO_1_DESCRIPTION, COL_PHOTO_2, COL_PHOTO_2_DESCRIPTION, COL_PHOTO_3, COL_PHOTO_3_DESCRIPTION,
            COL_PHOTO_4, COL_PHOTO_4_DESCRIPTION, COL_PHOTO_5, COL_PHOTO_5_DESCRIPTION};

    // constructor TODO: may not use this one when implementing getInstance
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // the actual sql statement to create the table
    public static final String CREATE_PRESCHOOLS_TABLE = "CREATE TABLE " + DATABASE_NAME +
            " (" +
            COL_ID + " PRIMARY KEY AUTOINCREMENT NOT NULL " +
            COL_NAME + " TEXT " +
            COL_DESCRIPTION + " TEXT " +
            COL_MONTHLY_PRICE + " DOUBLE " +
            COL_STREET_ADDRESS + " TEXT " +
            COL_CITY + " TEXT " +
            COL_STATE + " TEXT " +
            COL_ZIP + " TEXT " +
            COL_TYPE + " TEXT " +
            COL_RATING + " INT " +
            COL_PHOTO_1 + " INT " +
            COL_PHOTO_1_DESCRIPTION + " TEXT " +
            COL_PHOTO_2 + " INT " +
            COL_PHOTO_2_DESCRIPTION + " TEXT " +
            COL_PHOTO_3 + " INT " +
            COL_PHOTO_3_DESCRIPTION + " TEXT " +
            COL_PHOTO_4 + " INT " +
            COL_PHOTO_4_DESCRIPTION + " TEXT " +
            COL_PHOTO_5 + " INT " +
            COL_PHOTO_5_DESCRIPTION + " TEXT );";



    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
