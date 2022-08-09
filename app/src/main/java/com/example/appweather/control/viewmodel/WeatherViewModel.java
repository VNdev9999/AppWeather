package com.example.appweather.control.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.appweather.model.entity.ResponseWeather;
import com.example.appweather.control.repository.WeatherRepository;

public class WeatherViewModel extends ViewModel {
    private WeatherRepository weatherRepository;

    public WeatherViewModel() {
        init();
    }

    public void init() {
        weatherRepository = new WeatherRepository();
    }

    public LiveData<ResponseWeather> getWeatherResponseLiveData(String lat, String lon) {
        Log.d("WeatherViewModel", "getWeatherResponseLiveData: lat = "+lat +" lon = "+lon);
        return weatherRepository.getDashBoardWeather(lat, lon);
    }
}
