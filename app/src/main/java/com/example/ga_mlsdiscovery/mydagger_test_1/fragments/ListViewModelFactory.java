package com.example.ga_mlsdiscovery.mydagger_test_1.fragments;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.ga_mlsdiscovery.mydagger_test_1.repository.GetDataService;

public class ListViewModelFactory implements ViewModelProvider.Factory {

    private double lat;
    private double lon;
    private GetDataService getDataService;

    public ListViewModelFactory(double lat , double lon, GetDataService service){
        this.lat = lat;
        this.lon = lon;
        this.getDataService = service;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ListViewModel(lat,lon, getDataService);
    }
}
