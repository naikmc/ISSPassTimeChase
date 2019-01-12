package com.example.ga_mlsdiscovery.mydagger_test_1.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ISSResponse {

    @SerializedName("response")
    private List<Passtime> response;

    public List<Passtime> getResponse() {
        return response;
    }

    public void setResponse(List<Passtime> response) {
        this.response = response;
    }
}
