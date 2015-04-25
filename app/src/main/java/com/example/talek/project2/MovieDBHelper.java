package com.example.talek.project2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MovieDBHelper extends SQLiteOpenHelper {

    private static final String name = "movies.sqlite3";
    private static final int version = 2;


    public MovieDBHelper(Context ctx) {
        super(ctx, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE movies (" +
                "_id integer primary key autoincrement," +
                "fastA int default 0," +
                "thorA int default 0," +
                "takenA int default 0);";
        db.execSQL(sql);
    }

  //  SELECT SUM(credit) cr, SUM(value*credit) gp From course;

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS movies;";
        db.execSQL(sql);
        this.onCreate(db);
    }
}
