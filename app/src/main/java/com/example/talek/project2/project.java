package com.example.talek.project2;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

/**
 * Created by talek on 4/25/15 AD.
 */
public class project extends ActionBarActivity {
    String user;
    MovieDBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        helper = new MovieDBHelper(this);
//
//        helper = new MovieDBHelper(this);
//        Intent data = this.getIntent();
//
//        level = data.getStringExtra("difficulty");
//        min = data.getIntExtra("minutes",0);
//        sec =data.getIntExtra("seconds",0);
//        sMin = Integer.toString(min);
//        sSec = Integer.toString(sec);
//
//        TextView tv = (TextView) findViewById(R.id.textView16);
//        tv.setText(String.format("  %02d:%02d", min, sec));

    }

    public void buttonClicked(View v) {
        EditText etUser = (EditText) findViewById(R.id.editText);
        user = etUser.getText().toString().trim();

        if (user.length() > 0 && user.equals("rama9")) {
            Intent a = new Intent(this, insertName.class);
            a.putExtra("user", user);
            startActivity(a);
        } else if (user.length() > 0 && user.equals("rama3")) {
            Intent a = new Intent(this, insertName.class);
            a.putExtra("user", user);
            startActivity(a);
        }else if (user.length() > 0 && user.equals("rama2")) {
            Intent a = new Intent(this, insertName.class);
            a.putExtra("user", user);
            startActivity(a);
        }else if (user.length() > 0 && user.equals("siam")) {
            Intent a = new Intent(this, insertName.class);
            a.putExtra("user", user);
            startActivity(a);
        }else if (user.length() > 0 && user.equals("rangsit")) {
            Intent a = new Intent(this, insertName.class);
            a.putExtra("user", user);
            startActivity(a);
        }
        else {
            Toast t = Toast.makeText(this.getApplication(), "Cannot login", Toast.LENGTH_SHORT);
            t.show();
        }


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_project, menu);
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



//    class PostMessageTask extends AsyncTask<String, Void, Boolean> {
//        String line;
//        StringBuilder buffer = new StringBuilder();
//
//        @Override
//        protected Boolean doInBackground(String... params) {
//            String user = params[0];
//            String message = params[1];
//            String minutes = params[2];
//            String seconds = params[3];
//            HttpClient h = new DefaultHttpClient();
//            HttpPost p = new HttpPost("http://ict.siit.tu.ac.th/~u5522770148/its333/post.php");
//
//            List<NameValuePair> values = new ArrayList<NameValuePair>();
//            values.add(new BasicNameValuePair("user", user));
//            values.add(new BasicNameValuePair("level", message));
//            values.add(new BasicNameValuePair("minutes", minutes));
//            values.add(new BasicNameValuePair("seconds", seconds));
//            try {
//                p.setEntity(new UrlEncodedFormEntity(values));
//                HttpResponse response = h.execute(p);
//                BufferedReader reader = new BufferedReader(
//                        new InputStreamReader(response.getEntity().getContent()));
//                while((line = reader.readLine()) != null) {
//                    buffer.append(line);
//
//                }
//
//                return true;
//            } catch (UnsupportedEncodingException e) {
//                Log.e("Error", "Invalid encoding");
//            } catch (ClientProtocolException e) {
//                Log.e("Error", "Error in posting a message");
//            } catch (IOException e) {
//                Log.e("Error", "I/O Exception");
//            }
//
//
//            return false;
//        }
//
//        @Override
//        protected void onPostExecute(Boolean result) {
//            if (result) {
//                Toast t = Toast.makeText(insertName.this.getApplicationContext(),
//                        "Successfully post ",
//                        Toast.LENGTH_SHORT);
//                t.show();
//
//
//            }
//
//            else {
//                Toast t = Toast.makeText(insertName.this.getApplicationContext(),
//                        "Unable to post your status",
//                        Toast.LENGTH_SHORT);
//                t.show();
//            }
//        }
//    }


