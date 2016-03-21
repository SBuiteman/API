/**
 * Created by Stijn on 17/03/2016.
 */
package com.example.stijn.imdbappstijn;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * model ArrayList to store MovieData objects
 */
public class MovieDataList {

    // fields
    private ArrayList<MovieData> movieList;

    // constructor
    public MovieDataList () {
        movieList = new ArrayList<>();
    }

    // methods
    public ArrayList getMovies () {
        return movieList;
    }

    public void setResults (ArrayList<MovieData> moviesArg) {
        movieList = moviesArg;
    }
}
