package com.example.rplrus25.midsemester12rpl;


import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class fragment_nowplaying extends Fragment {

    public ArrayList<ItemObject> ItemObjectArrayList;
    ItemObject ItemObject;
    RecyclerView recyclerView;
    MahasiswaHelper mahasiswaHelper;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_fragment_nowplaying, container, false);
        recyclerView =(RecyclerView) rootView.findViewById(R.id.nowPlaying);
        mahasiswaHelper = new MahasiswaHelper(rootView.getContext());
        new datafragment().execute();
        return rootView;
    }
    @SuppressLint("StaticFieldLeak")
    public class datafragment extends AsyncTask<Void, Void, JSONObject> {


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
                        ItemObjectArrayList.add(ItemObject);

                    }
                    RecyclerViewAdapter adapter = new RecyclerViewAdapter(rootView.getContext(), ItemObjectArrayList);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
                    recyclerView.setNestedScrollingEnabled(false);
                    recyclerView.setVisibility(rootView.VISIBLE);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
