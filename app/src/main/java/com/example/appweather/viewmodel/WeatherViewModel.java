package com.example.appweather.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appweather.gson.ResponseWeather;
import com.example.appweather.repository.WeatherRepository;
import com.example.appweather.response.WeatherResponse;

public class WeatherViewModel extends ViewModel {
    private WeatherRepository weatherRepository;

    public WeatherViewModel() {
        init();
    }

    public void init() {
        weatherRepository = new WeatherRepository();
        Log.e("ttttttttttt", "init: viewmodel" +weatherRepository);
    }

    public LiveData<ResponseWeather> getWeatherResponseLiveData() {
        Log.e("TAG", "getWeatherResponseLiveData: "+ weatherRepository.getDashBoardWeather().getValue());
        return weatherRepository.getDashBoardWeather();
    }
}
