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
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

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
    SignInButton signInButton;
    GoogleSignInClient mGoogleSignInClient;
    GoogleSignInAccount account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signInButton= findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);
        account = GoogleSignIn.getLastSignedInAccount(this);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;
                }
            }
        });




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
    protected  void  onStart() {
        super.onStart();
        if (account!=null){
            Intent intent= new Intent(getApplicationContext(), Home.class);
            startActivity(intent);
            finish();
        }
    }
    private void signIn() {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, MAIN_ACTIVITY_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == MAIN_ACTIVITY_REQUEST_CODE) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            Log.d("hasil", "handleSignInResult: " + account.getEmail());
            Log.d("hasil", "handleSignInResult: " + account.getId());
            Log.d("hasil", "handleSignInResult: " + account.getGivenName());
            Intent intent = new Intent(getApplicationContext(), Home.class);
            startActivity(intent);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("", "signInResult:failed code=" + e.getStatusCode());
        }
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode ,Intent data){
//        SharedPreferences sharedPreferences = getSharedPreferences("login" , Context.MODE_PRIVATE);
//        boolean stateValue = sharedPreferences.getBoolean("setloggingOut", false);
//        if (requestCode == MAIN_ACTIVITY_REQUEST_CODE){
//             if (!stateValue){
//                 finish();
//             }else {
//                 updateLoginState(false);
//                 super.onActivityResult(requestCode, resultCode, data);
//             }
//        }else {
//            super.onActivityResult(requestCode, resultCode, data);
//        }
//    }
//
//    private void updateLoginState(boolean b) {
//    }


}
