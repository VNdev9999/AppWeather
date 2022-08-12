package com.example.appweather.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.appweather.databinding.LayoutDialogBinding;
import com.example.appweather.ultilties.Constants;
import com.example.appweather.ultilties.ConvertDate;

public class DetailDialog extends DialogFragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LayoutDialogBinding binding = LayoutDialogBinding.inflate(inflater, container, false);
        Bundle bundle = getArguments();
        String nameCountry = bundle.getString(Constants.KEY_COUNTRY);
        String sunRise = bundle.getString(Constants.KEY_RISE);
        String sunSet = bundle.getString(Constants.KEY_SET);
        String maxTemp = bundle.getString(Constants.KEY_MAX);
        String minTemp = bundle.getString(Constants.KEY_MIN);

        binding.tvRise.setText(ConvertDate.convertValueDateToString(Integer.parseInt(sunRise)));
        binding.tvSunSet.setText(ConvertDate.convertValueDateToString(Integer.parseInt(sunSet)));
        binding.tvMaxTemperature.setText(Math.round(Float.parseFloat(maxTemp) - 273.15) + "\u2103");
        binding.tvMinTemperature.setText(Math.round(Float.parseFloat(minTemp) - 273.15) + "\u2103");
        binding.tvCountry.setText(nameCountry);

        return binding.getRoot();
    }


}
