package com.example.ga_mlsdiscovery.mydagger_test_1;

import android.app.Application;
import android.content.Context;

import com.example.ga_mlsdiscovery.mydagger_test_1.di.component.DaggerMyAppComponent;
import com.example.ga_mlsdiscovery.mydagger_test_1.di.component.MyAppComponent;
import com.example.ga_mlsdiscovery.mydagger_test_1.di.module.MyAppModule;
import com.example.ga_mlsdiscovery.mydagger_test_1.di.module.NetModule;
import com.example.ga_mlsdiscovery.mydagger_test_1.di.support.AppContext;

import javax.inject.Inject;

import retrofit2.Retrofit;
import timber.log.Timber;

public class MyApp extends Application {

    @Inject
    @AppContext
    Context context;

    private MyAppComponent myAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        myAppComponent = DaggerMyAppComponent.builder()
                .netModule(new NetModule("http://api.open-notify.org/"))
                .myAppModule(new MyAppModule(this))
                .build();

        myAppComponent.inject(this);

        Timber.d("@@@ APP context" + context);
    }

    public MyAppComponent getMyAppComponent() {
        return myAppComponent;
    }
}
