package com.example.solarsystem.ui.sunshine;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class SunshineViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public SunshineViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is sunshine fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
