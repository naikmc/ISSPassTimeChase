package com.example.ga_mlsdiscovery.mydagger_test_1.di.component;


import android.content.Context;

import com.example.ga_mlsdiscovery.mydagger_test_1.MyApp;
import com.example.ga_mlsdiscovery.mydagger_test_1.di.module.MyAppModule;
import com.example.ga_mlsdiscovery.mydagger_test_1.di.module.NetModule;
import com.example.ga_mlsdiscovery.mydagger_test_1.di.support.AppContext;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {MyAppModule.class, NetModule.class})
public interface MyAppComponent {

    void inject(MyApp myApp);

     Retrofit getRetrofit();


    @AppContext
    Context getContext();
}
