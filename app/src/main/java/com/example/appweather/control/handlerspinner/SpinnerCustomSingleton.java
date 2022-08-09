package com.example.appweather.control.handlerspinner;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

public class SpinnerCustomSingleton {

    private static SpinnerCustomSingleton INSTANCE;
    private CallbackSpinnerItemSelected callbackSpinnerItemSelected;
    private String name;

    public SpinnerCustomSingleton(CallbackSpinnerItemSelected callbackSpinnerItemSelected) {
        this.callbackSpinnerItemSelected = callbackSpinnerItemSelected;
    }

    public static synchronized SpinnerCustomSingleton getInstance(CallbackSpinnerItemSelected callbackSpinnerItemSelected) {
        if (INSTANCE != null) {
            return INSTANCE;
        } else {
            return new SpinnerCustomSingleton(callbackSpinnerItemSelected);
        }
    }

    public void itemSpinnerSelected(Spinner addTittleSpinner, Context context) {
        addTittleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                name = (String) parent.getSelectedItem();
                callbackSpinnerItemSelected.spinnerItemValue(name);
                Toast.makeText(context, name, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}
