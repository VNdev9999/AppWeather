package com.example.appweather.response;

import com.example.appweather.gson.ResponseWeather;

import java.util.List;

public class WeatherResponse {
    private List<ResponseWeather> listWeatherApi;

    public List<ResponseWeather> getListWeatherApi() {
        return listWeatherApi;
    }

    public void setListWeatherApi(List<ResponseWeather> listWeatherApi) {
        this.listWeatherApi = listWeatherApi;
    }
}
