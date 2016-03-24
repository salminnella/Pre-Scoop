package com.example.anthony.prescoop.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anthony.prescoop.DBHelper.DatabaseHelper;
import com.example.anthony.prescoop.R;

/**
 * Created by anthony on 3/16/16.
 */
public class DBCursorAdapter extends android.widget.CursorAdapter {

    public DBCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }


    @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(R.layout.list_items,parent,false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            view.setBackgroundResource(android.R.color.white);
            view.setBackgroundResource(R.drawable.rounded_transparent);
            TextView preschoolName = (TextView) view.findViewById(R.id.name_text_list_items);
            TextView preschoolAddress = (TextView) view.findViewById(R.id.address_text_list_items);
            ImageView preschoolRating = (ImageView) view.findViewById(R.id.rating_image_list_items);
            ImageView preschoolFavorite = (ImageView) view.findViewById(R.id.favorite_image_list_items);

            preschoolName.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NAME)));
            String fullAddress = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_STREET_ADDRESS)) + " " +
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_CITY)) + " " +
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_STATE)) + ", " +
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_ZIP));

            preschoolAddress.setText(fullAddress);
            preschoolRating.setImageResource(getRatingImage(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_RATING))));

            if (cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_FAVORITE)) == 1) {
                preschoolFavorite.setImageResource(R.drawable.ic_favorite_red_24dp);
            }

        }

    private int getRatingImage(int rating){
        switch(rating){
            case 1:
                return R.drawable.one_star;
            case 2:
                return R.drawable.two_stars;
            case 3:
                return R.drawable.three_stars;
            case 4:
                return R.drawable.four_stars;
            case 5:
                return R.drawable.five_stars;
            default:
                return 0;
        }
    }
}
