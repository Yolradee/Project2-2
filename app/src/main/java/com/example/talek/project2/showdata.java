package com.example.talek.project2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by talek on 4/25/15 AD.
 */
public class showdata extends Activity{

    String user;
    int AA,BB,CC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show);
        Toast.makeText(showdata.this, "Your information!", Toast.LENGTH_SHORT).show();

        Intent data = this.getIntent();
        user = data.getStringExtra("user");
        AA = data.getIntExtra("f", 0);
        BB = data.getIntExtra("t", 0);
        CC = data.getIntExtra("ta", 0);

        MovieDBHelper helper = new MovieDBHelper(this);
        SQLiteDatabase Rdb = helper.getReadableDatabase();
        Cursor cursor = Rdb.rawQuery("SELECT f,t,ta FROM movies ", null);
        cursor.moveToFirst();


        if (user.equals("rama9")) {
            TextView tv = (TextView) findViewById(R.id.t1);
            tv.setText("Rama9");

            TextView tv1 = (TextView) findViewById(R.id.t2);
            tv1.setText(String.format("%d",cursor.getInt(0)));


        } else if (user.equals("rama3")) {
            TextView tv = (TextView) findViewById(R.id.t1);
            tv.setText("Rama3");
        } else if (user.equals("rama2")) {
            TextView tv = (TextView) findViewById(R.id.t1);
            tv.setText("Rama2");
        } else if (user.equals("siam")) {
            TextView tv = (TextView) findViewById(R.id.t1);
            tv.setText("Siam");
        } else if (user.equals("rangsit")) {
            TextView tv = (TextView) findViewById(R.id.t1);
            tv.setText("Rangsit");
        }




//
//        TextView tv1 = (TextView) findViewById(R.id.t2);
//        tv1.setText(String.format("%d",cursor.getInt(0)));
//
//        TextView tv2 = (TextView) findViewById(R.id.t3);
//        tv2.setText(String.format("%d",cursor.getInt(1)));
//
//        TextView tv3 = (TextView) findViewById(R.id.t4);
//        tv3.setText(String.format("%d",cursor.getInt(2)));
    }

//        }else if(user.equals("rama3")) {
//            TextView tv = (TextView) findViewById(R.id.t1);
//            tv.setText("Branch : Rama3");
//            TextView tv1 = (TextView) findViewById(R.id.t2);
//            tv1.setText("f");
//            TextView tv2 = (TextView) findViewById(R.id.t3);
//            tv2.setText("t");
//            TextView tv3 = (TextView) findViewById(R.id.t4);
//            tv3.setText("ta");
//        }else if(user.equals("rama2")) {
//            TextView tv = (TextView) findViewById(R.id.t1);
//            tv.setText("Branch : Rama2");
//            TextView tv1 = (TextView) findViewById(R.id.t2);
//            tv1.setText("f");
//            TextView tv2 = (TextView) findViewById(R.id.t3);
//            tv2.setText("t");
//            TextView tv3 = (TextView) findViewById(R.id.t4);
//            tv3.setText("ta");
//        }else if(user.equals("siam")) {
//            TextView tv = (TextView) findViewById(R.id.t1);
//            tv.setText("Branch : Siam");
//            TextView tv1 = (TextView) findViewById(R.id.t2);
//            tv1.setText("f");
//            TextView tv2 = (TextView) findViewById(R.id.t3);
//            tv2.setText("t");
//            TextView tv3 = (TextView) findViewById(R.id.t4);
//            tv3.setText("ta");
//        }else if(user.equals("rangsit")) {
//            TextView tv = (TextView) findViewById(R.id.t1);
//            tv.setText("Branch : Rangsit");
//            TextView tv1 = (TextView) findViewById(R.id.t2);
//            tv1.setText("f");
//            TextView tv2 = (TextView) findViewById(R.id.t3);
//            tv2.setText("t");
//            TextView tv3 = (TextView) findViewById(R.id.t4);
//            tv3.setText("ta");
//        }


    public void addClicked(View v){

        Intent i = new Intent(this, insertName.class);
        startActivity(i);
    }


    public void rankClicked(View v) {

        Intent i;
        i = new Intent(this, rank.class);
        startActivity(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show, menu);


        return true;
    }
    protected void onActivityResult(int resultCode,Intent data){


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









