package com.example.ga_mlsdiscovery.mydagger_test_1.fragments;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.ga_mlsdiscovery.mydagger_test_1.MainActivity;
import com.example.ga_mlsdiscovery.mydagger_test_1.R;
import com.example.ga_mlsdiscovery.mydagger_test_1.di.component.MyActivityComponent;
import com.example.ga_mlsdiscovery.mydagger_test_1.di.support.MyActScope;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    EditText lattitude;
    EditText logitude;

    Button fetchLocation;
    Button showPassTime;

    DetailViewModel detailViewModel;
    private MainActivity mActivity;

    @Inject
    public FusedLocationProviderClient mFusedLocationClient;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MyActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
        }
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    public MyActivityComponent getActivityComponent() {
        if (mActivity != null) {
            return mActivity.getActivityComponent();
        }
        return null;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            MainActivity activity = (MainActivity) context;
            this.mActivity = activity;
        }
    }

    @SuppressLint("MissingPermission")
    public void updateLocation() {

        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            detailViewModel.setLat(location.getLatitude());
                            detailViewModel.setLon(location.getLongitude());
                        }
                    }

                }).addOnFailureListener(getActivity(), new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Timber.d("@@@@ Faliure " + e);
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        lattitude = view.findViewById(R.id.lat_edit_text);
        logitude = view.findViewById(R.id.lon_edit_text);
        fetchLocation = view.findViewById(R.id.fetch_location_btn);
        showPassTime = view.findViewById(R.id.get_pass_time_btn);

        fetchLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    checkPermission();
                }
                updateLocation();
            }
        });

        showPassTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment,  ListFragment.instance(detailViewModel.getLat().getValue().doubleValue(), detailViewModel.getLon().getValue().doubleValue()))
                        .addToBackStack(null)
                        .commit();
            }
        });

        observeViewModel();
    }


    private void observeViewModel() {
        detailViewModel.getLat().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(@Nullable Double aDouble) {
                lattitude.setText(aDouble.toString());
            }
        });

        detailViewModel.getLon().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(@Nullable Double aDouble) {
                logitude.setText(aDouble.toString());
            }
        });
    }

    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                ) {//Can add more as per requirement

            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    123);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        updateLocation();
    }
}
