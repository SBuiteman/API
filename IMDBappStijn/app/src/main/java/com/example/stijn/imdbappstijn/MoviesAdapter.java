/**
 * Created by Stijn on 14/03/2016.
 */
package com.example.stijn.imdbappstijn;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * This class contains a getView method which takes in an ArrayList containing MovieData objects.
 */
public class MoviesAdapter extends ArrayAdapter<MovieData> {

    // fields
    public Context layoutMAContext;

    // constructor
    public MoviesAdapter(Context context, ArrayList<MovieData> dataList) {
        super(context, R.layout.list_item_layout, dataList);

        layoutMAContext = context;
    }

    // methods

    /**
     * handles the layout of the ListView
     */
    public View getView(int position, View view, ViewGroup parent) {

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) layoutMAContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item_layout, parent, false);
        }

        // get each MovieData object
        MovieData listItem = getItem(position);

        // get name and release from object and place in TV's
        TextView nameTextView = (TextView) view.findViewById(R.id.movieTitle);
        TextView yearTextView = (TextView) view.findViewById(R.id.movieYear);

        String nameText = (listItem.getItemTitle());
        String yearText = (listItem.getItemRelease());

        nameTextView.setText(nameText);
        yearTextView.setText(yearText);

        return view;
    }
}