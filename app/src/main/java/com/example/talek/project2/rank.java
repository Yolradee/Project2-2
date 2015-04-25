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
import android.view.View;
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

    ArrayList<HashMap<String, String>> data;
    MovieDBHelper helper;
    SimpleAdapter adapter;
    Handler h;
    Handler handler;
    String F,T,Ta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        data = new ArrayList<HashMap<String, String>>();


        adapter= new SimpleAdapter(this, data, R.layout.col,
                new String[] {"Fast7", "Thor", "Taken"},
                new int[] {R.id.nm1, R.id.nm2, R.id.nm3});

        ListView l = (ListView)findViewById(R.id.listView);
        l.setAdapter(adapter);
        LoadNumberTask task = new LoadNumberTask();
        task.execute();
 }

    public void addClicked(View v){

        Intent i = new Intent(this, insertName.class);
        startActivity(i);
    }


    public void showClicked(View v) {

        Intent i = new Intent(this, showdata.class);
        startActivity(i);
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

           int fastL;
           int thorL;
           int takenL;


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

//                   Log.e("LoadNumberTask", buffer.toString());

                   //Parsing JSON and displaying messages
                   JSONObject json = new JSONObject(buffer.toString());
                   JSONArray jmsg = json.getJSONArray("msg");


                       JSONObject jmessage = jmsg.getJSONObject(0);

                       fastL = jmessage.getInt("Fast7");
                       thorL = jmessage.getInt("Thor");
                       takenL = jmessage.getInt("Taken");


                       HashMap<String, String> item;
                       item = new HashMap<String, String>();

                         if(fastL>=thorL && thorL >=takenL) {
                             item.put("Fast7", String.format("Fast7  " + "%d",fastL));
                             item.put("Thor", String.format("Thor  " + "%d",thorL));
                             item.put("Taken", String.format("Taken  " + "%d",takenL));
                             data.add(0, item);
                         }
                          if(fastL>=thorL && thorL <=takenL) {
                             item.put("Fast7", String.format("Fast7  "+ "%d",fastL));
                             item.put("Thor", String.format("Thor  "+ "%d",takenL));
                             item.put("Taken", String.format("Taken  " + "%d",thorL));
                             data.add(0, item);
                   }
                         if(fastL<=thorL && fastL >=takenL) {
                             item.put("Fast7", String.format("Thor  "+ "%d",thorL));
                             item.put("Thor", String.format("Fast7  " + "%d",fastL));
                             item.put("Taken", String.format("Taken  " +"%d",takenL));
                             data.add(0, item);
                   }
                        if(fastL<=thorL && fastL <=takenL) {
                            item.put("Fast7", String.format("Thor  "+ "%d",thorL));
                            item.put("Thor", String.format("Taken  " +"%d",takenL));
                            item.put("Taken", String.format("Fast7  "+ "%d",fastL));
                            data.add(0, item);
                   }
                        if(fastL>=thorL && fastL <=takenL) {
                            item.put("Fast7", String.format("Taken  " +"%d",takenL));
                            item.put("Thor", String.format("Fast7  " + "%d",fastL));
                            item.put("Taken", String.format("Thor  "+ "%d",thorL));
                            data.add(0, item);
                   }
                        if(fastL<=thorL && fastL <=takenL) {
                            item.put("Fast7", String.format("Taken  " + "%d",takenL));
                            item.put("Thor", String.format("Thor  " + "%d",thorL));
                            item.put("Taken", String.format("Fast7  " +"%d",fastL));
                            data.add(0, item);
                   }


               }


               return true;


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
                   Log.e("LoadNumberTask", "Successfully Load");
               } else {
                   Log.e("LoadNumberTask", "Unable Load");

               }
           }
       }
}
