package com.example.ga_mlsdiscovery.mydagger_test_1.fragments;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class DetailViewModel extends ViewModel {

    private MutableLiveData<Double> lat = new MutableLiveData<>();

    private MutableLiveData<Double> lon = new MutableLiveData<>();

    public void setLat(double lat) {

        this.lat.setValue(lat);
    }

    public void setLon(double lon) {

        this.lon.setValue(lon);
    }

    public LiveData<Double> getLat() {

        return lat;
    }

    public LiveData<Double> getLon() {

        return lon;
    }



}
