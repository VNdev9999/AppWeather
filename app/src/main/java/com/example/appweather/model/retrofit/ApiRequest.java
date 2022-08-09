package com.example.appweather.model.retrofit;

import com.example.appweather.model.entity.ResponseWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRequest {
    @GET("data/2.5/weather")
    Call<ResponseWeather> getWeather(@Query("lat") String lat,
                                     @Query("lon") String lon,
                                     @Query("appid") String appid);
}
