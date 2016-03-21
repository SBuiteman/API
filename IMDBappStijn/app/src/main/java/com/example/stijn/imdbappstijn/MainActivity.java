/**
 * Stijn Buiteman
 * 11072783
 * 21/03/16
 */
package com.example.stijn.imdbappstijn;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * This class displays a list of movies via MovieAdapter, TagAsyncTask and HttpRequestHelper. The
 * list is stored in MovieDataList. onItemClick the SingleMovieActivity is launched.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // fields
    public Button movieTitleButton;
    public EditText userInput;
    public MoviesAdapter moviesAdapter;
    public MovieDataList movieDataList;
    public ListView movieListView;
    public String finalSearchKeyWord;

    /**
     * onCreate initialises the fields and onItemClick, calls executeAsync if Instance state was
     * saved.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // call to init
        init();

        // check if InstanceState saved
        if (savedInstanceState != null) {
            executeAsync(savedInstanceState.getString("key"));
        }

        /**
         * onItemClick launch SingleMovieActivity and pass title of movie clicked
         */
        movieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String title = ((TextView) view.findViewById(R.id.movieTitle)).getText().toString();

                Intent intent = new Intent(getApplicationContext(),
                        SingleMovieActivity.class);
                intent.putExtra("key", title);
                startActivity(intent);
            }
        });
    }

    // methods

    /**
     * on button click userinput is converted to fit in url and TagAsycTask is executed
     * */
    @Override
    public void onClick(View v) {
        String searchKeyWord = userInput.getText().toString();

        // place s= before string and replace spaces with %20
        finalSearchKeyWord = "s=" + searchKeyWord;
        finalSearchKeyWord = finalSearchKeyWord.replaceAll(" ", "%20");

        // call method executeAsync
        executeAsync(finalSearchKeyWord);
    }

    /**
     * called from TagAsyncTask to fill list with searchresults
     */
    public void displayResults(ArrayList<MovieData> searchResults) {
        movieDataList.setResults(searchResults);

        // link adapter to listview
        moviesAdapter = new MoviesAdapter(this, movieDataList.getMovies());
        movieListView.setAdapter(moviesAdapter);
    }

    /**
     * initialize views
     */
    public void init() {

        userInput = (EditText) findViewById(R.id.userInput);

        movieTitleButton = (Button) findViewById(R.id.Movie);

        movieTitleButton.setOnClickListener(this);

        movieDataList = new MovieDataList();

        movieListView = (ListView) findViewById(R.id.list);
    }

    /**
     * launch TagAsyncTask and pass edited searchterm
     */
    public void executeAsync(String finalSearchKeyWordArg) {
        TagAsyncTask asyncTask = new TagAsyncTask(this);
        asyncTask.execute(finalSearchKeyWordArg);
    }

    /**
     * save edited searchterm
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.getString("key", finalSearchKeyWord);
    }
}

