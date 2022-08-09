package com.example.appweather.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Rain implements Serializable {

    @SerializedName("1h")
    @Expose
    public Float _1h;

}
