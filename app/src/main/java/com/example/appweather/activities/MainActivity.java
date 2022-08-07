package com.example.appweather.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.appweather.databinding.ActivityMainBinding;
import com.example.appweather.gson.ResponseWeather;
import com.example.appweather.viewmodel.WeatherViewModel;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    WeatherViewModel weatherViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.d("TAG", "onCreate: " + binding);
        weatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        weatherViewModel.init();
        getData();

    }

    private void getData() {
        weatherViewModel.getWeatherResponseLiveData().observe(this, responseWeather -> {
            Log.e("TAG", "getData1: ");
            if (responseWeather != null) {
                Log.e("TAG", "getData2: ");
//                binding.tvNameCity.setText(responseWeather.name);
            //    Log.e("TAG", "getData3: "+responseWeather.name );
            }

        });
    }
}