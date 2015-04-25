package com.example.talek.project2;

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
    SimpleAdapter adapter;
    Handler h;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        MovieDBHelper helper = new MovieDBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();


        LoadNumberTask task = new LoadNumberTask();
        task.execute();

    }


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
