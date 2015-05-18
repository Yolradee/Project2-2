package com.example.talek.project2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.app.Activity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by talek on 4/25/15 AD.
 */
public class showdata extends ActionBarActivity implements AdapterView.OnItemClickListener {

    ArrayList<HashMap<String, String>> d;
    SimpleCursorAdapter adapter;
    String user;
    int AA,BB,CC;
    Cursor myData;
    MovieDBHelper helper;
    ListView listView1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show);


        Intent data = this.getIntent();
        user = data.getStringExtra("user");


        if (user.equals("rama9")) {
            TextView tv = (TextView) findViewById(R.id.t1);
            tv.setText("Rama9");
        } else if (user.equals("rama3")) {
            TextView tv1 = (TextView) findViewById(R.id.t1);
            tv1.setText("Rama3");
        } else if (user.equals("rama2")) {
            TextView tv2 = (TextView) findViewById(R.id.t1);
            tv2.setText("Rama2");
        } else if (user.equals("siam")) {
            TextView tv3 = (TextView) findViewById(R.id.t1);
            tv3.setText("Siam");
        } else if (user.equals("rangsit")) {
            TextView tv4 = (TextView) findViewById(R.id.t1);
            tv4.setText("Rangsit");
        }


        System.out.println(user + " " + AA + " " + BB + " " + CC);

        final MovieDBHelper myDb = new MovieDBHelper(this);
        String myData[] = myDb.SelectAllData();

        // listView1
        final ListView listView1 = (ListView) findViewById(R.id.listView);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, myData);

        listView1.setAdapter(adapter);

//        listView1.setOnItemLongClickListener(this);

        listView1.setOnItemClickListener(this);
    }

            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                long idd = id+1;
                System.out.println(idd);
                Log.d("movies", "" + idd);
                Toast t1 = Toast.makeText(this.getApplicationContext(),
                            "Id : " + idd +" is clicked ",
                            Toast.LENGTH_SHORT);
                    t1.show();
            }

//
//            public boolean onItemLongClick(AdapterView<?> parent, View view,
//                                           int position, long id) {
//
//                SQLiteDatabase dbw;
//                dbw = helper.getWritableDatabase();
//
//                int n = dbw.delete("movies", "_id = ?",
//                        new String[]{Long.toString(id)});
//
//                if (n == 1) {
//                    Toast t = Toast.makeText(this.getApplicationContext(),
//                            "Successfully deleted the selected item.",
//                            Toast.LENGTH_SHORT);
//                    t.show();
//
////                    // retrieve a new collection of records
////                    String strSQL = "SELECT fastA,thorA,takenA FROM movies";
////                    Cursor cursor = db.rawQuery(strSQL, null);
////                    // update the adapter
////                    adapter.changeCursor(cursor);
//
//                } else {
//                    Toast t = Toast.makeText(this.getApplicationContext(),
//                            "Failed deleted the selected item.",
//                            Toast.LENGTH_SHORT);
//                    t.show();
//                }
//                dbw.close();
//                return true;
//            }


            @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.menu_show, menu);


                return true;
            }



            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                // Handle action bar item clicks here. The action bar will
                // automatically handle clicks on the Home/Up button, so long
                // as you specify a parent activity in AndroidManifest.xml.
                int id = item.getItemId();


                //noinspection SimplifiableIfStatement
                if (id == R.id.action_settings) {
                    return true;
                }

                return super.onOptionsItemSelected(item);
            }

}