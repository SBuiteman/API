/**
 * Created by Stijn on 14/03/2016.
 */
package com.example.stijn.imdbappstijn;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Modelclass to contain retreived data
 */
public class MovieData implements Comparable<MovieData> {
    // fields
    private String release;
    private String title;

    // constructor
    public MovieData(String releaseArg, String titleArg) {
        release = releaseArg;
        title = titleArg;
    }

    // Methods
    public String getItemTitle() {
        return title;
    }

    public String getItemRelease() {
        return release;
    }

    /**
     * methods used to sort list on release
     */
    public int compareTo(MovieData years) {
        return ReleaseComparator.RELEASE.compare(this, years);
    }

    public static class ReleaseComparator {
        public static Comparator<MovieData> RELEASE = new Comparator<MovieData>() {
            @Override
            public int compare(MovieData movie1, MovieData movie2) {
                return movie1.release.compareTo(movie2.release);
            }
        };
    }
}

