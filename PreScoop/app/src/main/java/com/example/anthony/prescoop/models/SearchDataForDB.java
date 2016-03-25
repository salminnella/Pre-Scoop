package com.example.anthony.prescoop.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anthony on 3/24/16.
 */
public class SearchDataForDB implements Parcelable {
    private String[] searchCriteria;
    private String[] columns;

    // search constructor
    public SearchDataForDB(String name, String range, String rating, String price) {
        searchCriteria = new String[4];
        searchCriteria[0] = name;
        searchCriteria[1] = range;
        searchCriteria[2] = rating;
        searchCriteria[3] = price;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(this.searchCriteria);
        //dest.writeStringArray(this.columns);
    }

    private SearchDataForDB(Parcel in) {
        this.searchCriteria = new String[4];
        in.readStringArray(this.searchCriteria);
//        this.columns = new String[5];
//        in.readStringArray(this.columns);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<SearchDataForDB> CREATOR = new Parcelable.Creator<SearchDataForDB>(){
        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public SearchDataForDB createFromParcel(Parcel in) {
            return new SearchDataForDB(in);
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public SearchDataForDB[] newArray(int size) {
            return new SearchDataForDB[size];
        }
    };


    // getters and setters
    public String[] getSearchCriteria() {
        return searchCriteria;
    }

    public String getSearchSchoolName() {
        return searchCriteria[0];
    }

    public String getSearchRange() {
        return searchCriteria[1];
    }

    public String getSearchRating() {
        return searchCriteria[2];
    }

    public String getSearchPrice() {
        return getSearchCriteria()[3];
    }
    public void setSearchCriteria(String[] searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }
}
