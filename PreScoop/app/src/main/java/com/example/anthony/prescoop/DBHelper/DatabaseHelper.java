package com.example.anthony.prescoop.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by anthony on 3/15/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "PRESCOOP";

    // table and columns
    public static final String PRESCHOOL_TABLE_NAME = "preschools";
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
    public static final String COL_FAVORITE = "favorite";
    // TODO add favorite column

    // builds all columns in one array for queries later on
    public static final String[] COLUMNS = {COL_ID, COL_NAME, COL_DESCRIPTION, COL_MONTHLY_PRICE,
            COL_STREET_ADDRESS, COL_CITY, COL_STATE, COL_ZIP, COL_TYPE, COL_RATING, COL_PHOTO_1,
            COL_PHOTO_1_DESCRIPTION, COL_PHOTO_2, COL_PHOTO_2_DESCRIPTION, COL_PHOTO_3, COL_PHOTO_3_DESCRIPTION,
            COL_PHOTO_4, COL_PHOTO_4_DESCRIPTION, COL_PHOTO_5, COL_PHOTO_5_DESCRIPTION, COL_FAVORITE};



    // the actual sql statement to create the table
    public static final String CREATE_PRESCHOOLS_TABLE = "CREATE TABLE IF NOT EXISTS " + PRESCHOOL_TABLE_NAME +
            " (" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            COL_NAME + " TEXT, " +
            COL_DESCRIPTION + " TEXT, " +
            COL_MONTHLY_PRICE + " DOUBLE, " +
            COL_STREET_ADDRESS + " TEXT, " +
            COL_CITY + " TEXT, " +
            COL_STATE + " TEXT, " +
            COL_ZIP + " TEXT, " +
            COL_TYPE + " TEXT, " +
            COL_RATING + " INT, " +
            COL_PHOTO_1 + " INT, " +
            COL_PHOTO_1_DESCRIPTION + " TEXT, " +
            COL_PHOTO_2 + " INT, " +
            COL_PHOTO_2_DESCRIPTION + " TEXT, " +
            COL_PHOTO_3 + " INT, " +
            COL_PHOTO_3_DESCRIPTION + " TEXT, " +
            COL_PHOTO_4 + " INT, " +
            COL_PHOTO_4_DESCRIPTION + " TEXT, " +
            COL_PHOTO_5 + " INT, " +
            COL_PHOTO_5_DESCRIPTION + " TEXT, " +
            COL_FAVORITE + " BIT );";


    // makes sure there is only one instance of the database
    // if there isn't one, make it, otherwise return the one instance
    private static DatabaseHelper instance;

    public static DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context);
        }

        return instance;
    }

    // database constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PRESCHOOLS_TABLE);
      }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PRESCHOOL_TABLE_NAME);
        this.onCreate(db);
    }

    public void insertIntoPreschools(String name, int schoolDescription, double price, String address, String city,
                                     String state, String zip, String type, int rating, int photo1, String photo1Description,
                                     int photo2, String photo2Description, int photo3, String photo3Description, int photo4,
                                     String photo4Description, int photo5, String photo5Description) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_DESCRIPTION, schoolDescription);
        values.put(COL_MONTHLY_PRICE, price);
        values.put(COL_STREET_ADDRESS, address);
        values.put(COL_CITY, city);
        values.put(COL_STATE, state);
        values.put(COL_ZIP, zip);
        values.put(COL_TYPE, type);
        values.put(COL_RATING, rating);
        values.put(COL_PHOTO_1, photo1);
        values.put(COL_PHOTO_1_DESCRIPTION, photo1Description);
        values.put(COL_PHOTO_2, photo2);
        values.put(COL_PHOTO_2_DESCRIPTION, photo2Description);
        values.put(COL_PHOTO_3, photo3);
        values.put(COL_PHOTO_3_DESCRIPTION, photo3Description);
        values.put(COL_PHOTO_4, photo4);
        values.put(COL_PHOTO_4_DESCRIPTION, photo4Description);
        values.put(COL_PHOTO_5, photo5);
        values.put(COL_PHOTO_5_DESCRIPTION, photo5Description);

        db.insert(PRESCHOOL_TABLE_NAME, null, values);
    }

    public void deleteFromPreschools() {

    }

    /**
     * Retrieves preschools from the database in a cursor
     * @return
     */
    public Cursor getPreschools() {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(PRESCHOOL_TABLE_NAME, COLUMNS, null, null, null, null, null, null);
        cursor.moveToFirst();

        return cursor;
    }

    public Cursor searchPreschools(String query) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(PRESCHOOL_TABLE_NAME, COLUMNS,
                COL_NAME + " LIKE ? COLLATE NOCASE",
                new String[]{ "%" + query + "%" },
                null,
                null,
                null,
                null);
        cursor.moveToFirst();
        return cursor;
    }
}
