/**
 * Created by Stijn on 14/03/2016.
 */
package com.example.stijn.imdbappstijn;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class handles the request to the API to get a JSON-string
 */
public class HttpRequestHelper {

    // url in two pieces to place userinput in between
    private static final String url1 = "http://www.omdbapi.com/?";
    private static final String url2 = "&r=json";

    /**
     * download data, return JSON-string
     */
    protected static synchronized String downLoadFromServer(String... params) {

        //initialize return value
        String returnValue = "";

        // get searchword chosen by user
        String chosenTag = params[0];

        // get complete url from Strings
        String complete_url = url1 + chosenTag + url2;

        URL url = null;
        try{
            url = new URL(complete_url);
        }catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // make connection
        HttpURLConnection connection;
        if (url != null) {
            try {
                connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("GET");

                // read the response
                Integer response = connection.getResponseCode();

                // check if response is OK
                if (200 <= response && response <= 299) {

                    // read in data
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    while((line = br.readLine()) != null) {
                        returnValue = returnValue + line;
                    }
                }
                else {
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        // return read data
        return returnValue;
    }
}
