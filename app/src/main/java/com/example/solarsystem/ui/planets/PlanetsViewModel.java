package com.example.solarsystem.ui.planets;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class PlanetsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public PlanetsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is planets fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}