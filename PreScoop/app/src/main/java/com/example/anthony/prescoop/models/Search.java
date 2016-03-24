package com.example.anthony.prescoop.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anthony on 3/24/16.
 */
public class Search implements Parcelable {
    private String[] searchCriteria;
    private String[] columns;



    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(searchCriteria);
        dest.writeStringArray(columns);
    }

    private Search(Parcel source) {
        source.readStringArray(searchCriteria);
        source.readStringArray(columns);
//        searchCriteria = in.readStringArray();
//        columns = in.readStringArray();
    }


    // search constructor
    public Search (String name, String range, String rating, String price) {
        searchCriteria = new String[4];
        searchCriteria[0] = name;
        searchCriteria[1] = range;
        searchCriteria[2] = rating;
        searchCriteria[3] = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Search> CREATOR = new Parcelable.Creator<Search>(){
        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public Search createFromParcel(Parcel in) {
            return new Search(in);
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public Search[] newArray(int size) {
            return new Search[size];
        }
    };


    // getters and setters
    public String[] getSearchCriteria() {
        return searchCriteria;
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
