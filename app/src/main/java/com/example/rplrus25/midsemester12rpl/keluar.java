package com.example.rplrus25.midsemester12rpl;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class keluar extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");

        if (username.equals("")) {
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putInt("isLogged", 1);
//            editor.apply();

            Intent intent = new Intent(keluar.this, MainActivity.class);
            startActivity(intent);
            finish();

        }
        else if  (username == username){
            Intent intent = new Intent(keluar.this, Home.class);
            startActivity(intent);
            finish();
        }
    }
}