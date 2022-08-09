package com.example.appweather.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appweather.control.AppUtil;
import com.example.appweather.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadData();
        setActionLottie();
    }

    private void loadData() {
        if (AppUtil.isNetWorkAvailable(this)) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 3000);
        } else {
            Toast.makeText(this, "Network disconnected", Toast.LENGTH_SHORT).show();
        }
    }

    private void setActionLottie() {
        binding.lottieLayerName.animate().translationY(-2000).setDuration(2700).setStartDelay(0);
    }
}