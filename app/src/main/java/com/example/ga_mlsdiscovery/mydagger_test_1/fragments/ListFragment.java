package com.example.ga_mlsdiscovery.mydagger_test_1.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ga_mlsdiscovery.mydagger_test_1.MainActivity;
import com.example.ga_mlsdiscovery.mydagger_test_1.R;
import com.example.ga_mlsdiscovery.mydagger_test_1.adapter.RecyclerAdapter;
import com.example.ga_mlsdiscovery.mydagger_test_1.di.component.MyActivityComponent;
import com.example.ga_mlsdiscovery.mydagger_test_1.model.Passtime;
import com.example.ga_mlsdiscovery.mydagger_test_1.repository.GetDataService;

import java.util.List;
import java.util.Timer;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {


    private static final String LATITUDE = "LATITUDE" ;
    private static final String LOGITUDE = "LOGITUDE";

    private RecyclerAdapter recyclerAdapter;
    private RecyclerView recyclerView;

    private double lat;
    private double lon;

    private MainActivity mActivity;

    private ListViewModel listViewModel;

    @Inject
    GetDataService getDataService;

    public static ListFragment instance(double lat , double lon){
        ListFragment listFragment = new ListFragment();
        Bundle bundle = new Bundle();
        bundle.putDouble(LATITUDE, lat);
        bundle.putDouble(LOGITUDE, lon);
        listFragment.setArguments(bundle);
        return listFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        lat = getArguments().getDouble(LATITUDE);
        lon = getArguments().getDouble(LOGITUDE);

        setRetainInstance(true);

        Timber.d("@@@@@@lat"+ lat);
        Timber.d("@@@@@@Lon"+ lon);

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            MainActivity activity = (MainActivity) context;
            this.mActivity = activity;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Timber.d("@@@@@@onCreateView");
        MyActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
        }
View view =  inflater.inflate(R.layout.fragment_list_new, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        listViewModel = ViewModelProviders.of(this, new ListViewModelFactory(lat, lon, getDataService)).get(ListViewModel.class);
        recyclerView = view.findViewById(R.id.recycler_view);

       initRecyclerView();
        listViewModel.getPassTimes().observe(this, new Observer<List<Passtime>>() {
            @Override
            public void onChanged(@Nullable List<Passtime> passtimes) {
                recyclerAdapter.setNewValue(passtimes);
            }
        });
    }

    private void initRecyclerView() {
        recyclerAdapter = new RecyclerAdapter(listViewModel.getPassTimes().getValue());
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerAdapter);
    }
    public MyActivityComponent getActivityComponent() {
        if (mActivity != null) {
            return mActivity.getActivityComponent();
        }
        return null;
    }

}
