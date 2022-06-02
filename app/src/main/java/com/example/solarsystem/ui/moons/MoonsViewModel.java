package com.example.solarsystem.ui.moons;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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