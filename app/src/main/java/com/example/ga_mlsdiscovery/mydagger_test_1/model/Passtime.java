package com.example.ga_mlsdiscovery.mydagger_test_1.model;

import com.google.gson.annotations.SerializedName;

public class Passtime {
    @SerializedName("risetime")
    private  String timeStamp;
    @SerializedName("duration")
    private  String duration;

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }


}
