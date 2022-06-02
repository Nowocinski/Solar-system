package com.example.solarsystem.ui.sunshine;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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
