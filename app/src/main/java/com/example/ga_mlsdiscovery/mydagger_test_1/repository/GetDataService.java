package com.example.ga_mlsdiscovery.mydagger_test_1.repository;


import com.example.ga_mlsdiscovery.mydagger_test_1.model.ISSResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataService {

    @GET("iss-pass.json?")
    Call<ISSResponse> getISSPassTime(@Query("lat") String lat, @Query("lon") String lon);
}
