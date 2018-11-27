package com.example.rplrus25.midsemester12rpl;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rplrus25.midsemester12rpl.database.MahasiswaHelper;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class fragment_upcoming extends Fragment {
    public ArrayList<Result> ItemObjectArrayList=new ArrayList<>();
    RecyclerView recyclerView;
    MahasiswaHelper mahasiswaHelper;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment_upcoming, container, false);
        recyclerView =(RecyclerView) view.findViewById(R.id.upcoming);
        mahasiswaHelper = new MahasiswaHelper(view.getContext());
        load_data_from_json();;
       return view;
    }

    private void load_data_from_json() {
        final json_api service = retrofitclientinstance.getRetrofitInstance().create(json_api.class);
        Call<jsonRespond> call = service.getJsonNowPlaying();
        call.enqueue(new Callback<jsonRespond>() {
            @Override
            public void onResponse(Call<jsonRespond> call, Response<jsonRespond> response) {
                jsonRespond jsonRespond = response.body();
                ItemObjectArrayList = new ArrayList<>(Arrays.asList(jsonRespond.getResults()));
                Log.d("responku", "onResponse: " + jsonRespond.getResults());
                RecycleViewAdapterRetro adapter = new RecycleViewAdapterRetro(view.getContext(), ItemObjectArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<jsonRespond> call, Throwable t) {
                Log.d("responku", "onFailure: gagal");
            }
        });

    }
}
