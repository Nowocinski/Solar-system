package com.example.solarsystem.ui.moons;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class MoonsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MoonsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is moons fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}