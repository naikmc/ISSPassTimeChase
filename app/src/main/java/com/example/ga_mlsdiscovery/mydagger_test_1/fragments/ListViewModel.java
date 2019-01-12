package com.example.ga_mlsdiscovery.mydagger_test_1.fragments;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.ga_mlsdiscovery.mydagger_test_1.model.ISSResponse;
import com.example.ga_mlsdiscovery.mydagger_test_1.model.Passtime;
import com.example.ga_mlsdiscovery.mydagger_test_1.repository.GetDataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListViewModel extends ViewModel {

    private Double lat;
    private Double lon;
    private GetDataService getDataService;

    private MutableLiveData<List<Passtime>> mPassTime = new MutableLiveData<>();


    public ListViewModel(double lat , double lon, GetDataService service){
        this.lat = lat;
        this.lon = lon;
        this.getDataService = service;

        fetchData();
    }

    private void fetchData() {
        getDataService.getISSPassTime(lat.toString(), lon.toString()).enqueue(new Callback<ISSResponse>() {
            @Override
            public void onResponse(Call<ISSResponse> call, Response<ISSResponse> response) {
                mPassTime.postValue(response.body().getResponse());

            }

            @Override
            public void onFailure(Call<ISSResponse> call, Throwable t) {

            }
        });

    }



    public LiveData<List<Passtime>> getPassTimes() {
        return mPassTime;
    }

}
