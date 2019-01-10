package com.example.ga_mlsdiscovery.mydagger_test_1.di.module;

import android.app.Activity;
import android.content.Context;

import com.example.ga_mlsdiscovery.mydagger_test_1.di.support.ActivityContext;
import com.example.ga_mlsdiscovery.mydagger_test_1.di.support.MyActScope;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import dagger.Module;
import dagger.Provides;

@Module
public class MyActivityModule {

    private final Activity activity;

    public MyActivityModule(Activity activity){
        this.activity = activity;
    }

    @Provides
    @ActivityContext
    Context providesContext(){
        return activity;
    }

    @Provides
    @ActivityContext
    Activity providesActivity(){
        return activity;
    }

    @Provides
    @MyActScope
    FusedLocationProviderClient providesFusedLocationProviderClient(@ActivityContext Context context){
        return LocationServices.getFusedLocationProviderClient(context);
    }


}
