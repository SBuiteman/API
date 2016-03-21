/**
 * Created by Stijn on 14/03/2016.
 */
package com.example.stijn.imdbappstijn;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
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
public class TagAsyncTask extends AsyncTask<String, Integer, String> {

    // fields
    public HttpRequestHelper httpRequestHelper;
    private Context context;
    private MainActivity mainActivity;

    // constructor
    public TagAsyncTask(MainActivity mainActivity) {
        super();
        this.mainActivity = mainActivity;
        this.context = this.mainActivity.getApplicationContext();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // display data is being retreived
        Toast.makeText(context, R.string.dataRetreival,Toast.LENGTH_LONG).show();
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
            Toast.makeText(context, R.string.noResults, Toast.LENGTH_LONG).show();
        }
        else {

            // create Arraylist to contain results
            ArrayList<MovieData> moviedata = new ArrayList<>();

            // parse JSON
            try {
                JSONObject resultObject = new JSONObject(result);
                JSONArray movies = resultObject.getJSONArray("Search");

                // for each movie found get title and release
                for (int i = 0; i < movies.length(); i++) {
                    JSONObject movie = movies.getJSONObject(i);
                    String movieName = movie.getString("Title");
                    String movieRelease = movie.getString("Year");

                    // make MovieData objects and add to ArrayList
                    moviedata.add(new MovieData(movieRelease, movieName));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            // call sort function from MovieDataList
            Collections.sort(moviedata, MovieData.ReleaseComparator.RELEASE);

            // pass ArrayList to methods that calls adapter
            mainActivity.displayResults(moviedata);
        }
    }
}
