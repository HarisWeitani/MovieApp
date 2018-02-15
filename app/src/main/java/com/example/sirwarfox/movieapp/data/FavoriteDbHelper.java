package com.example.sirwarfox.movieapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.sirwarfox.movieapp.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sir.WarFox on 15/02/2018.
 */

public class FavoriteDbHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "favorite.db";
    private static final int DATABASE_VERSION = 1;
    public static final String LOGTAG = "FAVORITE";

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase db;

    public FavoriteDbHelper (Context context){
        super(context, DATABASE_NAME , null , DATABASE_VERSION);
    }

    public void open(){
        Log.i(LOGTAG, "Database Opened");
        db = dbhandler.getWritableDatabase();
    }

    public void close(){
        Log.i(LOGTAG, "Database Closed");
        dbhandler.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_FAVORITE_TABLE = "CREATE TABLE "+ FavoriteContract.FavoriteEntry.TABLE_NAME + " ( " +
                FavoriteContract.FavoriteEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FavoriteContract.FavoriteEntry.COLUMNS_MOVIEID + " INTEGER, " +
                FavoriteContract.FavoriteEntry.COLUMNS_TITLE + " TEXT NOT NULL, " +
                FavoriteContract.FavoriteEntry.COLUMNS_USERRATING + " REAL NOT NULL, " +
                FavoriteContract.FavoriteEntry.COLUMNS_POSTER_PATH + " TEXT NOT NULL, " +
                FavoriteContract.FavoriteEntry.COLUMNS_PLOT_SYNOPSIS + " TEXT NOT NULL, " +
                "); ";
        sqLiteDatabase.execSQL(SQL_CREATE_FAVORITE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + FavoriteContract.FavoriteEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addFavorite(Movie movie){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FavoriteContract.FavoriteEntry.COLUMNS_MOVIEID, movie.getId());
        values.put(FavoriteContract.FavoriteEntry.COLUMNS_TITLE, movie.getOriginal_title());
        values.put(FavoriteContract.FavoriteEntry.COLUMNS_USERRATING, movie.getVoteAvarage());
        values.put(FavoriteContract.FavoriteEntry.COLUMNS_POSTER_PATH, movie.getPosterPath());
        values.put(FavoriteContract.FavoriteEntry.COLUMNS_PLOT_SYNOPSIS, movie.getOverview());

        db.insert(FavoriteContract.FavoriteEntry.TABLE_NAME, null,values);
        db.close();

    }

    public void deleteFavorite(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(FavoriteContract.FavoriteEntry.TABLE_NAME, FavoriteContract.FavoriteEntry.COLUMNS_MOVIEID+ "=" +id,null);
    }

    public List<Movie> getAllFavorite(){
        String[] columns = {
                FavoriteContract.FavoriteEntry._ID,
                FavoriteContract.FavoriteEntry.COLUMNS_MOVIEID,
                FavoriteContract.FavoriteEntry.COLUMNS_TITLE,
                FavoriteContract.FavoriteEntry.COLUMNS_USERRATING,
                FavoriteContract.FavoriteEntry.COLUMNS_POSTER_PATH,
                FavoriteContract.FavoriteEntry.COLUMNS_PLOT_SYNOPSIS,
        };
        String sortOrder = FavoriteContract.FavoriteEntry._ID + " ASC";
        List<Movie> favoriteList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(FavoriteContract.FavoriteEntry.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                sortOrder);

        if(cursor.moveToFirst()){
            do {
                Movie movie = new Movie();
                movie.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMNS_MOVIEID))));
                movie.setOriginal_title(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMNS_TITLE)));
                movie.setVoteAvarage(Double.parseDouble(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMNS_USERRATING))));
                movie.setPosterPath(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMNS_POSTER_PATH)));
                movie.setOverview(cursor.getString(cursor.getColumnIndex(FavoriteContract.FavoriteEntry.COLUMNS_PLOT_SYNOPSIS)));

                favoriteList.add(movie);

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return favoriteList;
    }

}
