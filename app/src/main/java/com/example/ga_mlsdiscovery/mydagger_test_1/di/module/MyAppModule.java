package com.example.ga_mlsdiscovery.mydagger_test_1.di.module;

import android.app.Application;
import android.content.Context;

import com.example.ga_mlsdiscovery.mydagger_test_1.di.support.AppContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MyAppModule {

    private final Application application;

    public MyAppModule(Application application){
        this.application = application;
    }

    @Provides
    @AppContext
    public Context providesContext(){
        return this.application;
    }

    @Provides
    @Singleton
    public  Application providesApplication() {
        return application;
    }






}
