package com.example.rplrus25.midsemester12rpl;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.rplrus25.midsemester12rpl.database.DatabaseHelper;
import com.example.rplrus25.midsemester12rpl.database.MahasiswaHelper;
import com.example.rplrus25.midsemester12rpl.database.MahasiswaModel;

public class Detail_Name extends AppCompatActivity {

    ImageView iv_nama;
    TextView txtusername,tddeskripsi;
    Button btntrailer;
    FloatingActionButton fab;
    boolean flag = true;
    String name;
    String deskripsi;
    String gambar;
    String move_id ;
    MahasiswaHelper mahasiswaHelper;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__name);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        iv_nama = findViewById(R.id.ic_launcher);
        txtusername = findViewById(R.id.txtusername);
        tddeskripsi = findViewById(R.id.tddeskripsi);
        btntrailer = findViewById(R.id.btntrailer);
        fab = findViewById(R.id.fab);
        mahasiswaHelper = new MahasiswaHelper(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        name = bundle.getString("name");
        deskripsi = bundle.getString("deskripsi");
        gambar = bundle.getString("gambar");
        move_id = bundle.getString("id");
        txtusername.setText(name);
        tddeskripsi.setText(deskripsi);
        Glide.with(Detail_Name.this)
                .load(gambar)
                .into(iv_nama);
//        new ambilURLYoutube(move_id).execute((Void) null);
        btntrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String url = "https://youtu.be/u9Mv98Gr5pY";
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(i);
//                }

            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag){
                    mahasiswaHelper.open();
                    mahasiswaHelper.beginTransaction();
                    MahasiswaModel m = new MahasiswaModel(name, deskripsi, gambar);
                    mahasiswaHelper.insertTransaction(m);
                    mahasiswaHelper.setTransactionSuccess();
                    mahasiswaHelper.endTransaction();
                    mahasiswaHelper.close();
                    Toast.makeText(getApplicationContext(), "Tersimpan", Toast.LENGTH_SHORT).show();
                    fab.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_favorite_black_24dp));
                    flag = false;
                }else if(!flag){
                    mahasiswaHelper.open();
                    mahasiswaHelper.beginTransaction();
                    int a = mahasiswaHelper.delete(name);
                    mahasiswaHelper.endTransaction();
                    mahasiswaHelper.close();
                    fab.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_favorite_border_black_24dp));
                    flag = true;
                }
            }
        });
    }
    public  boolean onSupportNavigateUp(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
        finish();
        return  true;
    }

}