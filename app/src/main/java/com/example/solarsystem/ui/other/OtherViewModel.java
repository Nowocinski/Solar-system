package com.example.solarsystem.ui.other;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class OtherViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public OtherViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is other fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}