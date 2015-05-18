package com.example.talek.project2;


        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;

        import android.app.Activity;
        import android.content.ContentValues;
        import android.content.Intent;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.Cursor;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.support.v7.app.ActionBarActivity;
        import android.util.Log;
        import android.widget.SimpleAdapter;
        import android.widget.TextView;
        import android.widget.Toast;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.UnsupportedEncodingException;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.net.URLConnection;
        import android.app.Activity;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.Toast;

        import org.apache.http.HttpResponse;
        import org.apache.http.NameValuePair;
        import org.apache.http.client.ClientProtocolException;
        import org.apache.http.client.HttpClient;
        import org.apache.http.client.entity.UrlEncodedFormEntity;
        import org.apache.http.client.methods.HttpPost;
        import org.apache.http.impl.client.DefaultHttpClient;
        import org.apache.http.message.BasicNameValuePair;
        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;
        import java.util.logging.Handler;

public class insertName extends ActionBarActivity {

    Bitmap bm3DPie = null;
    String user;
    String fast;
    String thor;
    String taken;
    String ddate;

    final static String urlGoogleChart
            = "http://chart.apis.google.com/chart";
    final static String urlp3Api
            = "?chxs=0,000000,25&chxt=x&cht=p3&chdls=000000,20&chs=600x250&chco=FF9999|A3FF47|75D1FF&chl=Fast7|Thor|Taken&chd=t:";

    EditText  inputA, inputB, inputC, inputD;
    Button generate;
    ImageView pieChart;
    MovieDBHelper helper;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_name);

        Intent data = this.getIntent();
        user = data.getStringExtra("user");

          if(user.equals("rama9")) {
            TextView tv = (TextView) findViewById(R.id.textView2);
            tv.setText("Branch : Rama9");
        }else if(user.equals("rama3")) {
            TextView tv = (TextView) findViewById(R.id.textView2);
            tv.setText("Branch : Rama3");
        }else if(user.equals("rama2")) {
            TextView tv = (TextView) findViewById(R.id.textView2);
            tv.setText("Branch : Rama2");
        }else if(user.equals("siam")) {
            TextView tv = (TextView) findViewById(R.id.textView2);
            tv.setText("Branch : Siam");
        }else if(user.equals("rangsit")) {
              TextView tv = (TextView) findViewById(R.id.textView2);
              tv.setText("Branch : Rangsit");
         }

        inputD = (EditText) findViewById(R.id.ddata);
        inputA = (EditText) findViewById(R.id.adata);
        inputB = (EditText) findViewById(R.id.bdata);
        inputC = (EditText) findViewById(R.id.cdata);
        generate = (Button) findViewById(R.id.generate);
        pieChart = (ImageView) findViewById(R.id.pie);
        generate.setOnClickListener(generateOnClickListener);


    }

    Button.OnClickListener generateOnClickListener
            = new Button.OnClickListener() {

        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub

            String D = inputD.getText().toString();
            String A = inputA.getText().toString();
            int AA = Integer.parseInt(A);
            String B = inputB.getText().toString();
            int BB = Integer.parseInt(B);
            String C = inputC.getText().toString();
            int CC = Integer.parseInt(C);

            System.out.println(" "+ D  + " "+ AA + "   " + BB + "   " + CC);
            String urlRqs3DPie = urlGoogleChart
                    + urlp3Api
                    + AA + "," + BB + "," + CC;
            Log.d("R_url", urlRqs3DPie);
            LoadBitmap_atask loadbitmap = new LoadBitmap_atask();
            loadbitmap.execute(urlRqs3DPie);
            //   Bitmap bm3DPie = loadChart(urlRqs3DPie);
            helper = new MovieDBHelper(getApplicationContext());
            SQLiteDatabase db = helper.getWritableDatabase();
            ContentValues r = new ContentValues();
            r.put("date", D);
            r.put("fastA", AA);
            r.put("thorA", BB);
            r.put("takenA", CC);
            long new_id = db.insert("movies", null, r);
            System.out.println("id" + new_id);
            System.out.println(D+"    "+AA +"     "+BB+"   "+CC);

            ddate = inputD.getText().toString().trim();
            fast = inputA.getText().toString().trim();
            thor = inputB.getText().toString().trim();
            taken = inputC.getText().toString().trim();


            if (ddate.length()>=0 && fast.length() >= 0 && thor.length() >= 0 && taken.length() >= 0) {
                Toast t = Toast.makeText(insertName.this.getApplicationContext(),
                        "Insert to database",
                        Toast.LENGTH_SHORT);
                t.show();
                PostMessageTask p = new PostMessageTask();
                p.execute(fast, thor, taken);

//            } else if (fast.equals(" ") && thor.equals(" ") && taken.equals(" ")) {
//                Toast t = Toast.makeText(insertName.this.getApplicationContext(),
//                        "Please add information",
//                        Toast.LENGTH_SHORT);
//                t.show();
//
            }
        }
    };

    public void ShowClick(View v) {
        Intent i;
        i = new Intent(this, showdata.class);
        i.putExtra("user",user);
        i.putExtra("date",user);
        i.putExtra("fastA",fast);
        i.putExtra("thorA",thor);
        i.putExtra("takenA",taken);
        startActivity(i);
    }


    public void RankClick(View v) {
        Intent r = new Intent(this, rank.class);
        startActivity(r);
    }

    class LoadBitmap_atask extends AsyncTask<String, String, Boolean> {

        Bitmap in_bm3DPie = null;

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Boolean doInBackground(String... params) {
            in_bm3DPie = loadChart(params[0]);
            return true;
        }
        @Override
        protected void onPostExecute(Boolean result) {
            bm3DPie = in_bm3DPie;

            if (bm3DPie == null) {
                Toast.makeText(insertName.this,
                        "Problem in loading 3D Pie Chart",
                        Toast.LENGTH_LONG).show();
            } else {
                pieChart.setImageBitmap(bm3DPie);
            }
        }

        public Bitmap loadChart(String src) {
            try {
                URL url = new URL(src);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setReadTimeout(10000); // millis
                connection.setConnectTimeout(15000); // millis
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (IOException e) {
                // Log exception
                return null;
            }
        }

    }

    private Bitmap loadChart(String urlRqs) {
        Bitmap bm = null;
        InputStream inputStream = null;


        try {
            inputStream = OpenHttpConnection(urlRqs);
            bm = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return bm;
    }


    private InputStream OpenHttpConnection(String strURL) throws IOException {
        InputStream is = null;
        URL url = new URL(strURL);
        URLConnection urlConnection = url.openConnection();

        try {
            HttpURLConnection httpConn = (HttpURLConnection) urlConnection;
            httpConn.setRequestMethod("GET");
            if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                is = httpConn.getInputStream();
            }
        } catch (Exception ex) {
        }

        return is;


    }


    class PostMessageTask extends AsyncTask<String, Void, Boolean> {
        String line;
        StringBuilder buffer = new StringBuilder();

        @Override
        protected Boolean doInBackground(String... params) {

            String fastP = params[0];
            String thorP = params[1];
            String takenP = params[2];
            HttpClient h = new DefaultHttpClient();
            HttpPost p = new HttpPost("http://ict.siit.tu.ac.th/~u5522773787/its333/post.php");

            //////////////////////
            List<NameValuePair> values = new ArrayList<NameValuePair>();

            values.add(new BasicNameValuePair("Fast7", fastP));
            values.add(new BasicNameValuePair("Thor", thorP));
            values.add(new BasicNameValuePair("Taken", takenP));
            try {
                p.setEntity(new UrlEncodedFormEntity(values));
                HttpResponse response = h.execute(p);
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                return true;

            } catch (UnsupportedEncodingException e) {
                Log.e("Error", "Invalid encoding");
            } catch (ClientProtocolException e) {
                Log.e("Error", "Error in posting a message");
            } catch (IOException e) {
                Log.e("Error", "I/O Exception");
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
//                Toast t = Toast.makeText(project.this.getApplicationContext(),
//                        "Successfully post your status",
//                        Toast.LENGTH_SHORT);
//                t.show();
                Log.e("PostMessageTask", "Successfully Post");
            } else {
                Log.e("PostMessageTask", "unable Post");
            }
        }

    }
}








