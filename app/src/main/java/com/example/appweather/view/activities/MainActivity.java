package com.example.appweather.view.activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.appweather.R;
import com.example.appweather.control.handlerspinner.CallbackSpinnerItemSelected;
import com.example.appweather.control.handlerspinner.SpinnerCustomSingleton;
import com.example.appweather.control.viewmodel.WeatherViewModel;
import com.example.appweather.databinding.ActivityMainBinding;
import com.example.appweather.model.entity.ResponseWeather;
import com.example.appweather.ultilties.Constants;
import com.example.appweather.view.DetailDialog;
import com.example.appweather.view.adapter.CategoryAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CallbackSpinnerItemSelected {

    private ActivityMainBinding binding;

    private WeatherViewModel weatherViewModel;
    private ResponseWeather responseWeatherObject;
    private List<String> nameList;
    private HashMap<String, String> hashMapLon;
    private HashMap<String, String> hashMapLat;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    private String currentDateTime = sdf.format(new Date());
    private String itemSpinnerSelected;
    private CategoryAdapter categoryAdapter;

    private View layoutView;
    private Dialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        nameList = new ArrayList<>();
        hashMapLon = new HashMap<>();
        hashMapLat = new HashMap<>();
        categoryAdapter = new CategoryAdapter(this, R.layout.item_selected, nameList);
        weatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        responseWeatherObject = new ResponseWeather();

        initValueNameListForSpinner();
        initHashmapLat();
        initHashmapLong();
        binding.spnCategory.setAdapter(categoryAdapter);
        SpinnerCustomSingleton.getInstance(this).itemSpinnerSelected(binding.spnCategory, this);
        weatherViewModel.init();
        showDetailWeather();
    }

    private void initHashmapLat() {
        hashMapLat.put("HaiPhong", "20.865139");
        hashMapLat.put("HaNoi", "21.028511");
        hashMapLat.put("HaiDuong", "20.94099");
        hashMapLat.put("HoChiMinh", "10.762622");
    }

    private void initHashmapLong() {
        hashMapLon.put("HaiPhong", "106.683830");
        hashMapLon.put("HaNoi", "105.804817");
        hashMapLon.put("HaiDuong", "106.33302");
        hashMapLon.put("HoChiMinh", "106.660172");
    }

    private void initValueNameListForSpinner() {
        nameList.add("HaiPhong");
        nameList.add("HaNoi");
        nameList.add("HaiDuong");
        nameList.add("HoChiMinh");
    }

    @SuppressLint({"NotifyDataSetChanged", "SetTextI18n"})
    private void getData() {
        if (itemSpinnerSelected != null) {
            weatherViewModel.getWeatherResponseLiveData(hashMapLat.get(itemSpinnerSelected), hashMapLon.get(itemSpinnerSelected)).observe(
                    this, responseWeather -> {
                        if (responseWeather != null) {
                            responseWeatherObject = responseWeather;
                            binding.tvTime.setText(currentDateTime);
                            binding.tvTemperature.setText(Math.round(responseWeather.main.temp - 273.15) + "\u2103");
                            binding.tvSunRise.setText(Integer.toString(responseWeather.sys.sunrise));
                            binding.tvSunSet.setText(Integer.toString(responseWeather.sys.sunset));
                            binding.tvWindSpeed.setText(responseWeather.wind.speed + "");
                            binding.tvWindDeg.setText(responseWeather.wind.deg + "\u2103");
                            categoryAdapter.notifyDataSetChanged();
                        }
                    });
        }
    }

    @Override
    public void spinnerItemValue(String itemName) {
        itemSpinnerSelected = itemName;
        getData();
    }

    private void showDetailWeather() {
        binding.tvDetail.setOnClickListener(v -> {
            DetailDialog dialogFragment = new DetailDialog();
            Bundle bundle = new Bundle();
            bundle.putString(Constants.KEY_COUNTRY, responseWeatherObject.sys.country);
            bundle.putString(Constants.KEY_RISE, String.valueOf(responseWeatherObject.sys.sunrise));
            bundle.putString(Constants.KEY_SET, String.valueOf(responseWeatherObject.sys.sunset));
            bundle.putString(Constants.KEY_MAX, String.valueOf(responseWeatherObject.main.tempMax));
            bundle.putString(Constants.KEY_MIN, String.valueOf(responseWeatherObject.main.tempMin));
            dialogFragment.setArguments(bundle); // push value
            dialogFragment.show(getSupportFragmentManager().beginTransaction(), "Image Dialog");
        });
    }


}