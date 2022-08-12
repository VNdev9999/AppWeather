package com.example.appweather.control.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.appweather.control.repository.WeatherRepository;
import com.example.appweather.model.entity.LatLon;
import com.example.appweather.model.entity.ResponseWeather;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class WeatherViewModel extends ViewModel {
    private final String TAG = "WeatherViewModel";
    private WeatherRepository weatherRepository;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    private String currentDateTime = sdf.format(new Date());

    private List<String> listCityName;
    private HashMap<String, LatLon> latLonHashMap;
    private LiveData<ResponseWeather> responseWeatherLiveData;
    private String itemSpinnerSelected = "HaiPhong";
    private ResponseWeather responseWeather;
    private String lat, lon;

    public WeatherViewModel() {
        init();
    }

    public void init() {
        weatherRepository = new WeatherRepository();
        listCityName = new ArrayList<>();
        latLonHashMap = new HashMap<>();
        setListCityName();
        Log.e(TAG, "init: listStrig.size " + listCityName.size());
        setLatLonHashMap();
    }

    private void setLatLonHashMap() {
        latLonHashMap.put("HaiPhong", new LatLon("20.865139", "106.683830"));
        latLonHashMap.put("HaNoi", new LatLon("21.028511", "105.804817"));
        latLonHashMap.put("HaiDuong", new LatLon("20.94099", "106.33302"));
        latLonHashMap.put("HoChiMinh", new LatLon("10.762622", "106.660172"));
    }

    private void setListCityName() {
        listCityName.add("HaiPhong");
        listCityName.add("HaNoi");
        listCityName.add("HaiDuong");
        listCityName.add("HoChiMinh");
    }

    public List<String> getListCityName() {
        return listCityName;
    }

    public LiveData<ResponseWeather> getWeatherResponseLiveData() {
        lat = Objects.requireNonNull(latLonHashMap.get(getItemSpinnerSelected())).getLat();
        lon = Objects.requireNonNull(latLonHashMap.get(getItemSpinnerSelected())).getLon();
        responseWeatherLiveData = weatherRepository.getDashBoardWeather(lat, lon);
        return weatherRepository.getDashBoardWeather(lat, lon);
    }

    public void setItemSpinnerSelected(String itemSpinnerSelected) {
        this.itemSpinnerSelected = itemSpinnerSelected;
    }

    public String getItemSpinnerSelected() {
        return itemSpinnerSelected;
    }

    public String getCurrentDateTime() {
        return currentDateTime;
    }

    public ResponseWeather getResponseWeather() {
        Log.e(TAG, "getResponseWeather: " + responseWeather);
        return responseWeather;
    }

    public void setResponseWeather(ResponseWeather responseWeather) {
        this.responseWeather = responseWeather;
    }


}
