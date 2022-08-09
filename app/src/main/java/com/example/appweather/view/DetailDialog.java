package com.example.appweather.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.appweather.R;
import com.example.appweather.ultilties.Constants;

public class DetailDialog extends DialogFragment {

    private TextView tvSunRise, tvSunSet, tvMax, tvMin, tvCountry;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_dialog, container, false);
        tvSunRise = view.findViewById(R.id.tv_rise);
        tvSunSet = view.findViewById(R.id.tv_sun_set);
        tvMax = view.findViewById(R.id.tv_max_temperature);
        tvMin = view.findViewById(R.id.tv_min_temperature);
        tvCountry = view.findViewById(R.id.tv_country);

        Bundle bundle = getArguments();
        String nameCountry = (String) bundle.getString(Constants.KEY_COUNTRY);
        String sunRise = (String) bundle.getString(Constants.KEY_RISE);
        String sunSet = (String) bundle.getString(Constants.KEY_SET);
        String maxTemp = (String) bundle.getString(Constants.KEY_MAX);
        String minTemp = (String) bundle.getString(Constants.KEY_MIN);

        tvSunRise.setText(sunRise);
        tvSunSet.setText(sunSet);
        tvMax.setText(maxTemp);
        tvMin.setText(minTemp);
        tvCountry.setText(nameCountry);

        return view;
    }


}
