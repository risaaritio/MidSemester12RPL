package com.example.rplrus25.midsemester12rpl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.bumptech.glide.disklrucache.DiskLruCache;

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

public class MainActivity extends AppCompatActivity {

    private int MAIN_ACTIVITY_REQUEST_CODE ;
    EditText txtusername, txtPassword;
    Button btnlogin;
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtusername = (EditText) findViewById(R.id.txtusername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnlogin = (Button) findViewById(R.id.btnlogin);

        SharedPreferences.Editor editor;
        pref = getSharedPreferences("testap", MODE_PRIVATE);
        editor = pref.edit();
        editor.putString("login", "true");
        editor.commit();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtusername.getText().toString().equals("risa") && txtPassword.getText().toString().equals("admin")){
                    Toast.makeText(getApplicationContext(),"sukses", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,Home.class);

                    String username = txtusername.getText().toString();
                    SharedPreferences sharedPreferences = getSharedPreferences("login",Context. MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username" ,username);
                    editor.commit();
                    startActivity(intent);
                    finish();

                }else {
                    Toast.makeText(getApplicationContext(),"gagal", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode ,Intent data){
        SharedPreferences sharedPreferences = getSharedPreferences("login" , Context.MODE_PRIVATE);
        boolean stateValue = sharedPreferences.getBoolean("setloggingOut", false);
        if (requestCode == MAIN_ACTIVITY_REQUEST_CODE){
             if (!stateValue){
                 finish();
             }else {
                 updateLoginState(false);
                 super.onActivityResult(requestCode, resultCode, data);
             }
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void updateLoginState(boolean b) {
    }


}
