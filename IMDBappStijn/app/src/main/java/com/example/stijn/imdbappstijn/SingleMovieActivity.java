/**
 * Stijn Buiteman
 * 18/3
 */
package com.example.stijn.imdbappstijn;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;

/**
 * This class implements a simple adapter to create a list displaying information of a single
 * movie. The ArrayList is stored in InstanceState.
 */
public class SingleMovieActivity extends AppCompatActivity {

    // fields
    public ListView singleList;
    public ArrayList<String> safeArray;

    /**
     * if ArrayList is stored, create list, else call SingleMovieAsyncTask and pass title
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_movie);

        singleList = (ListView) findViewById(R.id.singleList);

        // if an ArrayList was saved
        if ( savedInstanceState != null) {
            makeArray(savedInstanceState.getStringArrayList("safeArray"));
        } else {

            // get title from clicked movie from intent
            String movieTitle = this.getIntent().getStringExtra("key");
            SingleMovieAsyncTask singleMovieAsyncTask = new SingleMovieAsyncTask(this);

            // edit string to fit url
            String finalSearchKeyWord = "t=" + movieTitle;
            finalSearchKeyWord = finalSearchKeyWord.replaceAll(" ", "%20");

            // call asynctask and pass searchterm
            singleMovieAsyncTask.execute(finalSearchKeyWord);
        }
    }

    // methods

    /**
     * put found ArrayList in constructed ArrayList, call showList method
     */
    public void makeArray (ArrayList<String> arrayArg) {
        ArrayList<String> infoSingleMovie = arrayArg;
        safeArray = arrayArg;

        showList(infoSingleMovie);
    }

    /**
     * Makes a simple Adapter to process an ArrayList with Strings and links to a ListView
     */
    public void showList (ArrayList<String> infoSingleMovieArg) {
        ListAdapter listAdapter = new ArrayAdapter<String>(SingleMovieActivity.this,
                R.layout.single_list_item_layout, R.id.singleMovie, infoSingleMovieArg);

        // link adapter to ListView
        singleList.setAdapter(listAdapter);
    }

    /**
     * save ArrayList
     * */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("safeArray", safeArray);
    }
}
