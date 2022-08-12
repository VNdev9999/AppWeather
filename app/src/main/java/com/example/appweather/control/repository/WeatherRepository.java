package com.example.appweather.control.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appweather.model.entity.ResponseWeather;
import com.example.appweather.model.retrofit.ApiRequest;
import com.example.appweather.model.retrofit.RetrofitRequest;
import com.example.appweather.ultilties.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {
    private final ApiRequest apiRequest;
    private MutableLiveData<ResponseWeather> data;

    public WeatherRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<ResponseWeather> getDashBoardWeather(String lat, String lon) {
        data = new MutableLiveData<>();
        apiRequest.getWeather(lat, lon, Constants.API_KEY)
                .enqueue(new Callback<ResponseWeather>() {
                    @Override
                    public void onResponse(Call<ResponseWeather> call, Response<ResponseWeather> response) {
                        if (response.body() != null) {
                            data.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseWeather> call, Throwable t) {
                        data.setValue(null);
                    }
                });

        return data;
    }
}
