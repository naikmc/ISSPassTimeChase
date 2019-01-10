package com.example.ga_mlsdiscovery.mydagger_test_1.di.component;

import android.app.Activity;

import com.example.ga_mlsdiscovery.mydagger_test_1.MainActivity;
import com.example.ga_mlsdiscovery.mydagger_test_1.di.module.MyActivityModule;
import com.example.ga_mlsdiscovery.mydagger_test_1.di.module.NetModule;
import com.example.ga_mlsdiscovery.mydagger_test_1.di.support.MyActScope;
import com.example.ga_mlsdiscovery.mydagger_test_1.fragments.DetailFragment;

import dagger.Component;

@MyActScope
@Component(dependencies = {MyAppComponent.class} , modules = {MyActivityModule.class})
public interface MyActivityComponent {

     void inject(MainActivity mainActivity);

     void inject(DetailFragment detailFragment);

}
