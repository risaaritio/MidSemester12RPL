package com.example.rplrus25.midsemester12rpl;

import retrofit2.Call;
import retrofit2.http.GET;

public interface json_api {
    @GET("now_playing?api_key=7b91b2135beb96ab098d2f376ee5658b")
    Call<jsonRespond>getJsonNowPlaying();
    @GET("upcoming?api_key=7b91b2135beb96ab098d2f376ee5658b")
    Call<jsonRespond> getJsonUpcoming();
}
