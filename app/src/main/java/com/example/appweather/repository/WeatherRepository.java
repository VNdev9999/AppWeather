package com.example.appweather.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appweather.gson.ResponseWeather;
import com.example.appweather.response.WeatherResponse;
import com.example.appweather.retrofit.ApiRequest;
import com.example.appweather.retrofit.RetrofitRequest;
import com.example.appweather.ultilties.Constants;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {

    private static final String TAG = WeatherRepository.class.getSimpleName();
    private final ApiRequest apiRequest;
    private MutableLiveData<ResponseWeather> data ;


    public WeatherRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        Log.e(TAG, "WeatherRepository: zzzzzz " + apiRequest);
    }

    public LiveData<ResponseWeather> getDashBoardWeather() {
       if(data == null){
           data = new MutableLiveData<>();
           apiRequest.getWeather(Constants.LAT, Constants.LON, Constants.API_KEY)
                   .enqueue(new Callback<ResponseWeather>() {
                       @Override
                       public void onResponse(Call<ResponseWeather> call, Response<ResponseWeather> response) {
                           if (response.body() != null) {
                               Log.e("AA",new Gson().toJson(response.body()));
                               Log.e("AA",response.body().name);
                               data.setValue(response.body());
                               Log.e(TAG, "onResponse: data.size "+data.getValue().name );
                           }
                       }

                       @Override
                       public void onFailure(Call<ResponseWeather> call, Throwable t) {
                           data.setValue(null);
                           Log.e(TAG, "onFailure: ");
                       }
                   });

       }
        Log.e(TAG, "getDashBoardWeather: live data" + data.getValue());
        return data;
    }
}
