package com.example.rplrus25.midsemester12rpl;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.rplrus25.midsemester12rpl.database.MahasiswaHelper;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    private LinearLayoutManager Layout1;


    RecyclerView gv_detail;
    Menu menu;
    ItemObject ItemObject;
    int index;
    ProgressBar progressBar;
    LinearLayout listdata,listload;
    RecyclerViewAdapter adapter;



    public ArrayList<ItemObject> ItemObjectArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        gv_detail = (RecyclerView) findViewById(R.id.gv_detail);
        listdata = (LinearLayout) findViewById(R.id.listdata);
        listload = (LinearLayout) findViewById(R.id.listload);;
        new ambildata().execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Exit:
                SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(Home.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            case R.id.Favorit:
                Intent a= new Intent( this, favorite.class);
                startActivity(a);
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @SuppressLint("StaticFieldLeak")
    public class ambildata extends AsyncTask<Void, Void, JSONObject> {


        @Override
        protected void onPreExecute(){

        }

        @Override
        protected JSONObject doInBackground(Void... params) {
            JSONObject jsonObject;
            try {
                String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=20c575d79976acb235d507c425aa44ca";
                System.out.println("url ku " + url);
                DefaultHttpClient httpClient = new DefaultHttpClient();

                HttpGet httpGet = new HttpGet(url);
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                InputStream inputStream = httpEntity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        inputStream, "iso-8859-1"
                ), 8);
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                inputStream.close();
                String json = stringBuilder.toString();
                System.out.println("json nya " + json);
                jsonObject = new JSONObject(json);
            } catch (Exception e) {
                System.out.println("json nya error " + e.toString());
                jsonObject = null;
            }
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            listload.setVisibility(View.GONE);
            listdata.setVisibility(View.VISIBLE);
            if (jsonObject!=null){
                try {

                    JSONArray Hasiljson = jsonObject.getJSONArray("results");
                    ItemObjectArrayList = new ArrayList<>();
                    for (int i = 0; i < Hasiljson.length(); i++) {
                        ItemObject = new ItemObject();
                        String urlku ="https://image.tmdb.org/t/p/w600_and_h900_bestv2/";
                        String gambar = Hasiljson.getJSONObject(i).getString("poster_path");
                        ItemObject.setName(Hasiljson.getJSONObject(i).getString("title"));
                        ItemObject.setGambar(urlku + gambar);
                        ItemObject.setDeskripsi(Hasiljson.getJSONObject(i).getString("overview"));
                        Toast.makeText(getApplicationContext(),ItemObject.getName(),Toast.LENGTH_SHORT).show();
                        ItemObjectArrayList.add(ItemObject);

                    }
                    adapter = new RecyclerViewAdapter(Home.this, ItemObjectArrayList);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    gv_detail.setLayoutManager(new LinearLayoutManager(Home.this));
                    gv_detail.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}