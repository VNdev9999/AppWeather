package com.example.appweather.retrofit;

import com.example.appweather.gson.ResponseWeather;
import com.example.appweather.response.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRequest {
    @GET("data/2.5/weather")
    Call<ResponseWeather> getWeather(@Query("lat") String lat,
                                     @Query("lon") String lon,
                                     @Query("appid") String appid);
}
