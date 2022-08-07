package com.example.appweather.retrofit;

import static com.example.appweather.ultilties.Constants.BASE_URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class    RetrofitRequest {
        private static Retrofit retrofit;
        public static Retrofit getRetrofitInstance(){
                if(retrofit == null){
                        retrofit = new Retrofit.Builder()
                                .baseUrl(BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                }
                return retrofit;
        }
}
