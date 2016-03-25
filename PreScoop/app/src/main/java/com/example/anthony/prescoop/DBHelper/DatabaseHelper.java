package com.example.anthony.prescoop.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.anthony.prescoop.models.PreSchool;
import com.example.anthony.prescoop.models.SearchDataForDB;

/**
 * Created by anthony on 3/15/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "PRESCOOP";

    Cursor cursor;
    SQLiteDatabase dbWrite = getWritableDatabase();
    SQLiteDatabase dbRead = getReadableDatabase();

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
    public static final String COL_REGION = "region";
    public static final String COL_RANGE = "range";
    public static final String COL_PHONE_NUMBER = "phoneNumber";
    public static final String COL_AGE_GROUP = "ageGroup";
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

    // builds all columns in one array for queries later on
    public static final String[] COLUMNS = {COL_ID, COL_NAME, COL_DESCRIPTION, COL_MONTHLY_PRICE,
            COL_STREET_ADDRESS, COL_CITY, COL_STATE, COL_ZIP, COL_TYPE, COL_RATING, COL_REGION, COL_RANGE,
            COL_PHONE_NUMBER, COL_AGE_GROUP,COL_PHOTO_1, COL_PHOTO_1_DESCRIPTION, COL_PHOTO_2, COL_PHOTO_2_DESCRIPTION,
            COL_PHOTO_3, COL_PHOTO_3_DESCRIPTION, COL_PHOTO_4, COL_PHOTO_4_DESCRIPTION, COL_PHOTO_5,
            COL_PHOTO_5_DESCRIPTION, COL_FAVORITE};

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
            COL_REGION + " TEXT, " +
            COL_RANGE + " INT, " +
            COL_PHONE_NUMBER + " TEXT, " +
            COL_AGE_GROUP + " TEXT, " +
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
            COL_FAVORITE + " INT );";


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

    /**
     * Populate the database with some preschools.
     *
     * @param name String
     * @param schoolDescription Int to a R.string address
     * @param price Double
     * @param address String
     * @param city String
     * @param state String
     * @param zip String
     * @param type String
     * @param rating Int
     * @param region String
     * @param range Int
     * @param phoneNumber String
     * @param age String - age group
     * @param favorite int
     * @param schoolImages int[]
     * @param imageDescription String[]
     */
    public void insertIntoPreschools(String name, int schoolDescription, double price, String address, String city,
                                     String state, String zip, String type, int rating, String region, int range,
                                     String phoneNumber, String age, int favorite, int[] schoolImages, String[] imageDescription) {
        //SQLiteDatabase db = getWritableDatabase();

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
        values.put(COL_REGION, region);
        values.put(COL_RANGE, range);
        values.put(COL_PHONE_NUMBER, phoneNumber);
        values.put(COL_AGE_GROUP, age);

        values.put(COL_PHOTO_1, schoolImages[0]);
        values.put(COL_PHOTO_1_DESCRIPTION, imageDescription[0]);
        values.put(COL_PHOTO_2, schoolImages[1]);
        values.put(COL_PHOTO_2_DESCRIPTION, imageDescription[1]);
        values.put(COL_PHOTO_3, schoolImages[2]);
        values.put(COL_PHOTO_3_DESCRIPTION, imageDescription[2]);
        values.put(COL_PHOTO_4, schoolImages[3]);
        values.put(COL_PHOTO_4_DESCRIPTION, imageDescription[3]);
        values.put(COL_PHOTO_5, schoolImages[4]);
        values.put(COL_PHOTO_5_DESCRIPTION, imageDescription[4]);
        values.put(COL_FAVORITE, favorite);

        dbWrite.insert(PRESCHOOL_TABLE_NAME, null, values);
    }


    /**
     * Retrieves all the preschools from the database, and returns a cursor
     * @return Cursor
     */
    public Cursor getAllPreschools() {
        cursor = dbRead.query(PRESCHOOL_TABLE_NAME, COLUMNS, null, null, null, null, COL_RATING + " DESC", null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }

    public Cursor searchDB(SearchDataForDB searchData) {
        String where = "name LIKE ? COLLATE NOCASE ";
        String[] args = {"%" + searchData.getSearchCriteria()[0] + "%"};

        Log.i("db", "school name is; " + searchData.getSearchCriteria()[0]);
        Log.i("db", "args; " + args[0]);
        //AND range <= ? AND rating = ? AND price <= ?
        //, searchData.getSearchRange(), searchData.getSearchRating(), searchData.getSearchPrice()

        cursor = dbRead.query(PRESCHOOL_TABLE_NAME, COLUMNS, where, args, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    /**
     * Searches the database in just the School Name column
     * @param name String
     * @return Cursor
     */
    public Cursor findPreschoolsByName(String name) {
        String where = "name LIKE ? COLLATE NOCASE ";
        String[] args = {"%" + name + "%"};

        cursor = dbRead.query(PRESCHOOL_TABLE_NAME, COLUMNS, where, args, null, null, COL_RATING + " DESC", null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    /**
     * Searches the database in just the School Range column
     * @param range String
     * @return Cursor
     */
    public Cursor findPreschoolsByRange(String range) {
        String where = "range <= ? ";
        String[] args = {range};

        cursor = dbRead.query(PRESCHOOL_TABLE_NAME, COLUMNS, where, args, null, null, COL_RATING + " DESC", null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    /**
     * Searches the database in just the School Rating column
     * @param rating String
     * @return Cursor
     */
    public Cursor findPreschoolsByRating(String rating) {
        String where = "rating = ? ";
        String[] args = {rating};

        cursor = dbRead.query(PRESCHOOL_TABLE_NAME, COLUMNS, where, args, null, null, COL_RATING + " DESC", null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    /**
     * Searches the database in just the School Price column
     * @param price
     * @return
     */
    public Cursor findPreschoolsByPrice(String price) {
        String where = "price > 0 AND price <= ? ";
        String[] args = {price};

        cursor = dbRead.query(PRESCHOOL_TABLE_NAME, COLUMNS, where, args, null, null, COL_RATING + " DESC", null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    /**
     * Searches the database in the Range, Rating, and Price columns
     * @param range
     * @param rating
     * @param price
     * @return
     */
    public Cursor findByRangeRatingPrice(String range, String rating, String price) {
        String where = "range <= ? AND rating = ? AND (price > 0 AND price <= ?) ";
        String[] args = {range, rating, price};

        cursor = dbRead.query(PRESCHOOL_TABLE_NAME, COLUMNS, where, args, null, null, COL_RATING + " DESC", null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor filterPreschoolList(String query) {
        cursor = dbRead.query(PRESCHOOL_TABLE_NAME,
                COLUMNS,
                COL_NAME + " LIKE ? COLLATE NOCASE",
                new String[]{"%" + query + "%"},
                null,
                null,
                null,
                null);
        cursor.moveToFirst();
        return cursor;
    }

    /**
     * From the details activity, a search based on the id of the preschool
     * is performed to retrieve all necessary details to display to the user
     *
     * @param id int
     * @return PreSchool object
     */
    public PreSchool retrievePreschool(int id) {
        cursor = dbRead.query(PRESCHOOL_TABLE_NAME, COLUMNS,
                COL_ID + "=" + id, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        int description = cursor.getInt(cursor.getColumnIndex(COL_DESCRIPTION));
        int rating = cursor.getInt(cursor.getColumnIndex(COL_RATING));
        int range = cursor.getInt(cursor.getColumnIndex(COL_RANGE));
        int favorite = cursor.getInt(cursor.getColumnIndex(COL_FAVORITE));
        double price = cursor.getDouble(cursor.getColumnIndex(COL_MONTHLY_PRICE));
        String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
        String schoolAddress = cursor.getString(cursor.getColumnIndex(COL_STREET_ADDRESS));
        String city = cursor.getString(cursor.getColumnIndex(COL_CITY));
        String state = cursor.getString(cursor.getColumnIndex(COL_STATE));
        String zip = cursor.getString(cursor.getColumnIndex(COL_ZIP));
        String type = cursor.getString(cursor.getColumnIndex(COL_TYPE));
        String region = cursor.getString(cursor.getColumnIndex(COL_REGION));
        String phoneNum = cursor.getString(cursor.getColumnIndex(COL_PHONE_NUMBER));
        String ageGroup = cursor.getString(cursor.getColumnIndex(COL_AGE_GROUP));

        int[] schoolImages = new int[5];
        schoolImages[0] = cursor.getInt(cursor.getColumnIndex(COL_PHOTO_1));
        schoolImages[1] = cursor.getInt(cursor.getColumnIndex(COL_PHOTO_2));

        cursor.close();

        return new PreSchool(name, description, price, schoolAddress, city, state, zip, phoneNum, region, range,
                type, ageGroup, rating, favorite, schoolImages, null);

    }

    public void setFavoriteSchool(int id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_FAVORITE, 1);

        dbWrite.update(PRESCHOOL_TABLE_NAME, contentValues, COL_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public void removeFavoriteSchool(int id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_FAVORITE, 0);

        dbWrite.update(PRESCHOOL_TABLE_NAME, contentValues, COL_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public Cursor findFavoritePreschools() {
        cursor = dbRead.query(PRESCHOOL_TABLE_NAME,
                COLUMNS,
                COL_FAVORITE + " = 1 ",
                null,
                null,
                null,
                COL_RATING + " DESC",
                null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }
}
