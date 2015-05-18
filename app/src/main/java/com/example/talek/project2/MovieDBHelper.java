package com.example.talek.project2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MovieDBHelper extends SQLiteOpenHelper {

    private static final String name = "movies.sqlite3";
    private static final int version = 4;


    public MovieDBHelper(Context ctx) {
        super(ctx, name, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE movies (" +
                "_id integer primary key autoincrement," +
                "date text not null," +
                "fastA integer default 0," +
                "thorA integer default 0," +
                "takenA integer default 0);";
        db.execSQL(sql);
    }



    // Select All Data
    public String[] SelectAllData() {
        // TODO Auto-generated method stub

        try {
            String arrData[] = null;
            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data

            String strSQL = "SELECT date,fastA,thorA,takenA FROM movies ORDER BY date ASC";
            Cursor cursor = db.rawQuery(strSQL, null);

            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getCount()];

                    int i= 0;
                    do {
                        arrData[i] = " Date :   " +cursor.getString(0) + "\n"
                                + " Fast7 :   " +cursor.getString(1) + "  Tickets" + "\n"
                                + " Thor :   " + cursor.getString(2) + "   Tickets"+ "\n"
                                + " Taken :   " + cursor.getString(3)  + "   Tickets";
                        i++;

                        System.out.println(cursor.getString(0)+cursor.getString(1)+cursor.getString(2)+cursor.getString(3));
                    } while (cursor.moveToNext());

                }
            }
            cursor.close();

            return arrData;

        } catch (Exception e) {
            return null;
        }

    }





    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS movies;";
        db.execSQL(sql);
        this.onCreate(db);
    }
}
