package com.example.rplrus25.midsemester12rpl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rplrus25.midsemester12rpl.database.MahasiswaHelper;
import com.example.rplrus25.midsemester12rpl.database.MahasiswaModel;

import java.util.ArrayList;

public class favorite extends AppCompatActivity {

      private  MahasiswaHelper mahasiswaHelper;
      private  LinearLayoutManager Layout;
      RecyclerView Rview;
      private ArrayList<MahasiswaModel> models;
      ModelAdapter adapter;
      TextView TextData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        Layout = new LinearLayoutManager(favorite.this);
        TextData = findViewById(R.id.TextData);
        Rview = findViewById(R.id.Recycler);
        Rview.setLayoutManager(Layout);
        mahasiswaHelper = new MahasiswaHelper(getApplicationContext());
        mahasiswaHelper.open();
        models = new ArrayList<>();

        if (mahasiswaHelper.getAllData() == null){
            TextData.setVisibility(View.VISIBLE);
        }
        else if (mahasiswaHelper.getAllData() !=null){
            models = mahasiswaHelper.getAllData();
            Rview.setVisibility(View.VISIBLE);
            Rview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            Rview.setAdapter(adapter);
        }
        models = mahasiswaHelper.getAllData();

    }
}
