package com.example.talek.project2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;


public class rank extends ActionBarActivity {

    ArrayList<Map<String, String>> data;
    MovieDBHelper helper;
    SimpleAdapter adapter;
    Handler h;
    Handler handler;

<<<<<<< HEAD
=======

>>>>>>> origin/master
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
<<<<<<< HEAD

        MovieDBHelper helper = new MovieDBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
=======
>>>>>>> origin/master

        LoadNumberTask task = new LoadNumberTask();
        task.execute();
       // data = new ArrayList<HashMap<String,String>>();

<<<<<<< HEAD
        LoadNumberTask task = new LoadNumberTask();
        task.execute();
=======
        adapter= new SimpleAdapter(this, data, R.layout.activity_project,
                new String[] {"F", "A", "K"}, new int[] {R.id.nm1, R.id.nm2, R.id.nm3});
>>>>>>> origin/master

        ListView l = (ListView)findViewById(R.id.listView);
        l.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // This method is called when this activity is put foreground.



//            TextView tvm1 = (TextView)findViewById(R.id.m1);
//            tvm1.setText(String.format("Fast 7"+"%d", tf));
//            TextView tvnm1 = (TextView)findViewById(R.id.nm1);
//            tvnm1.setText(String.format("%d", tf));


        }


//        TextView tvGP = (TextView)findViewById(R.id.nm1);
//        TextView tvCR = (TextView)findViewById(R.id.nm2);
//        TextView tvGPA = (TextView)findViewById(R.id.nm3);

//        tvGP.setText(String.format("%.1f", gp));
//        tvCR.setText(String.format("%d", cr));
//        tvGPA.setText(String.format("%.2f", gpa));

//        db.close();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rank, menu);
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
<<<<<<< HEAD
=======

>>>>>>> origin/master
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

   class LoadNumberTask extends AsyncTask<String, Void, Boolean> {

       @Override
       protected Boolean doInBackground(String... params) {
           BufferedReader reader;
           StringBuilder buffer = new StringBuilder();
           String line;

           String fastL;
           String thorL;
           String takenL;


           try {
               URL u = new URL("http://ict.siit.tu.ac.th/~u5522773787/its333/fetch.php");
               HttpURLConnection h = (HttpURLConnection) u.openConnection();
               h.setDoInput(true);
               h.connect();

               int response = h.getResponseCode();
               if (response == 200) {
                   reader = new BufferedReader(new InputStreamReader(h.getInputStream()));
                   while ((line = reader.readLine()) != null) {
                       buffer.append(line);
                   }

                   Log.e("LoadNumberTask", buffer.toString());

                   //Parsing JSON and displaying messages
                   JSONObject json = new JSONObject(buffer.toString());
                   JSONArray jmsg = json.getJSONArray("msg");

<<<<<<< HEAD

                       JSONObject jmessage = jmsg.getJSONObject(0);
                       String br = jmessage.getString("Branch");
                       String fast = jmessage.getString("Fast7");
                       String thor = jmessage.getString("Thor");
                       String taken = jmessage.getString("Taken");

                       Map<String, String> item = new HashMap<String, String>();
                       item.put("Branch", br);
                       item.put("Fast7", fast);
                       item.put("Thor", thor);
                       item.put("Taken", taken);
                       data.add(0, item);

                       System.out.println("Hellooooooooooooooo");

                   TextView tv = (TextView) findViewById(R.id.nm1);
                   tv.setText(String.format("%d",fast));
=======
                   for (int i = 0; i < jmsg.length(); i++) {
                       JSONObject jmessage = jmsg.getJSONObject(i);

                       fastL = jmessage.getString("Fast7");
                       thorL = jmessage.getString("Thor");
                       takenL = jmessage.getString("Taken");


                       HashMap<String, String> item;
                       item= new HashMap<String, String>();

                       item.put("Fast7", fastL);
                       item.put("Thor", thorL);
                       item.put("Taken", takenL);
                       data.add(0, item);
                       System.out.println("Totalllllll");
                   }
>>>>>>> origin/master


                   return true;

               }
           } catch (MalformedURLException e) {
               Log.e("LoadNumberTask", "Invalid URL");
           } catch (IOException e) {
               Log.e("LoadNumberTask", "I/O Exception");
           } catch (JSONException e) {
               Log.e("LoadNumberTask", "Invalid JSON");
           }
           return false;
       }

           protected void onPostExecute(Boolean result) {
               if (result) {
                   adapter.notifyDataSetChanged();
                   Toast t;
                   t = Toast.makeText(rank.this.getApplicationContext(),
                           "Updated the timeline",
                           Toast.LENGTH_SHORT);
                   t.show();
               }
           }
   }
}
