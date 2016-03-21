/**
 * Created by Stijn on 19/03/2016.
 */
package com.example.stijn.imdbappstijn;

import android.content.Context;
import android.media.Image;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class calls the HttpRequestHelper in doInBackground so that the main thread does not wait
 * for the data retrieval. The retreived JSONObject is processed in onPostExecute.
 */
public class SingleMovieAsyncTask extends AsyncTask<String, Integer, String> {

    // fields
    public HttpRequestHelper httpRequestHelper;
    private Context context;
    private SingleMovieActivity singleMovieActivity;

    // constructor
    public SingleMovieAsyncTask(SingleMovieActivity singleMovieActivity) {
        super();
        this.singleMovieActivity = singleMovieActivity;
        this.context = this.singleMovieActivity.getApplicationContext();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {

        // call HttpRequestHelper and pass searchterm
        return httpRequestHelper.downLoadFromServer(params);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        // alert user if nothing was found
        if (result.length() == 0) {
            Toast.makeText(context, "No results were found:(", Toast.LENGTH_LONG).show();
        }
        else {

            // create Arraylist to contain results
            ArrayList<String> movieInfo = new ArrayList<>();

            // parse JSON
            try {
                JSONObject resultObject = new JSONObject(result);

                // get strings from JSONObject
                String title = resultObject.getString("Title");
                String actors = resultObject.getString("Actors");
                String genre = resultObject.getString("Genre");
                String runtime = resultObject.getString("Runtime");
                String release = resultObject.getString("Released");
                String imdbRating = resultObject.getString("imdbRating");
                String plot = resultObject.getString("Plot");

                // Add Tag to strings and add to ArrayList
                movieInfo.add("Title: " + title);
                movieInfo.add("Actors: " + actors);
                movieInfo.add("Genre: " + genre);
                movieInfo.add("Runtime: " + runtime);
                movieInfo.add("Release: " + release);
                movieInfo.add("imdbRating: " + imdbRating);
                movieInfo.add("Plot: " + plot);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            // call makeArray method and pass ArrayList
            singleMovieActivity.makeArray(movieInfo);
        }
    }
}

