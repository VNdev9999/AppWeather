package com.example.appweather.view.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.appweather.R;
import com.example.appweather.control.viewmodel.WeatherViewModel;
import com.example.appweather.databinding.ActivityMainBinding;
import com.example.appweather.ultilties.Constants;
import com.example.appweather.ultilties.ConvertDate;
import com.example.appweather.view.DetailDialog;
import com.example.appweather.view.adapter.CategoryAdapter;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private ActivityMainBinding binding;

    private WeatherViewModel weatherViewModel;
    private CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        weatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        weatherViewModel.init();
        categoryAdapter = new CategoryAdapter(this, R.layout.item_selected, weatherViewModel.getListCityName());
        binding.spnCategory.setAdapter(categoryAdapter);
        spinnerEventClick();
        showDetailWeather();
    }

    private void spinnerEventClick() {
        binding.spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                weatherViewModel.setItemSpinnerSelected((String) parent.getSelectedItem());
                Log.e(TAG, "onItemSelected: " + parent.getSelectedItem());
                getData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @SuppressLint({"NotifyDataSetChanged", "SetTextI18n"})
    private void getData() {
        if (weatherViewModel.getItemSpinnerSelected() != null) {
            weatherViewModel.getWeatherResponseLiveData().observe(
                    this, responseWeather -> {
                        if (responseWeather != null) {
                            weatherViewModel.setResponseWeather(responseWeather);
                            binding.tvTime.setText(weatherViewModel.getCurrentDateTime());
                            binding.tvTemperature.setText(Math.round(responseWeather.main.temp - 273.15) + "\u2103");
                            binding.tvSunRise.setText(ConvertDate.convertValueDateToString(responseWeather.sys.sunrise));
                            binding.tvSunSet.setText(ConvertDate.convertValueDateToString(responseWeather.sys.sunset));
                            binding.tvWindSpeed.setText(responseWeather.wind.speed + " m/s");
                            categoryAdapter.notifyDataSetChanged();
                        }
                    });
        }
    }

    private void showDetailWeather() {
        binding.tvDetail.setOnClickListener(v -> {
            DetailDialog dialogFragment = new DetailDialog();
            Bundle bundle = new Bundle();
            bundle.putString(Constants.KEY_COUNTRY, weatherViewModel.getResponseWeather().sys.country);
            bundle.putString(Constants.KEY_RISE, String.valueOf(weatherViewModel.getResponseWeather().sys.sunrise));
            bundle.putString(Constants.KEY_SET, String.valueOf(weatherViewModel.getResponseWeather().sys.sunset));
            bundle.putString(Constants.KEY_MAX, String.valueOf(weatherViewModel.getResponseWeather().main.tempMax));
            bundle.putString(Constants.KEY_MIN, String.valueOf(weatherViewModel.getResponseWeather().main.tempMin));
            dialogFragment.setArguments(bundle); // push value
            dialogFragment.show(getSupportFragmentManager().beginTransaction(), "Image Dialog");
        });
    }
}