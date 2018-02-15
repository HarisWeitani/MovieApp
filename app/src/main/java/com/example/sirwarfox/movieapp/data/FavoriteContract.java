package com.example.sirwarfox.movieapp.data;

import android.provider.BaseColumns;

/**
 * Created by Sir.WarFox on 15/02/2018.
 */

public class FavoriteContract {

    public static final class FavoriteEntry implements BaseColumns{

        public static final String TABLE_NAME = "favorite";
        public static final String COLUMNS_MOVIEID = "movieid";
        public static final String COLUMNS_TITLE = "title";
        public static final String COLUMNS_USERRATING = "userrating";
        public static final String COLUMNS_POSTER_PATH = "posterpath";
        public static final String COLUMNS_PLOT_SYNOPSIS = "overview";


    }

}
