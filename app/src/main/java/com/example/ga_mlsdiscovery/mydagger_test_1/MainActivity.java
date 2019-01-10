package com.example.ga_mlsdiscovery.mydagger_test_1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.ga_mlsdiscovery.mydagger_test_1.di.component.DaggerMyActivityComponent;
import com.example.ga_mlsdiscovery.mydagger_test_1.di.component.MyActivityComponent;
import com.example.ga_mlsdiscovery.mydagger_test_1.di.module.MyActivityModule;
import com.example.ga_mlsdiscovery.mydagger_test_1.fragments.DetailFragment;
import com.example.ga_mlsdiscovery.mydagger_test_1.fragments.ListFragment;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import javax.inject.Inject;

import retrofit2.Retrofit;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    MyActivityComponent myActivityComponent;

    @Inject
    public FusedLocationProviderClient mFusedLocationClient;


    @Inject
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myActivityComponent = DaggerMyActivityComponent.builder()
                .myAppComponent(((MyApp) getApplication()).getMyAppComponent())
                .myActivityModule(new MyActivityModule(this))
                .build();
        myActivityComponent.inject(this);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment, new DetailFragment())
                    .addToBackStack(null)
                    .commit();
        }

    }

    public MyActivityComponent getActivityComponent() {

        return myActivityComponent;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
