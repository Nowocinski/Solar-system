package com.example.solarsystem.ui.planets;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PlanetsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PlanetsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is planets fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setText(String text) {
        this.mText.setValue(this.mText.getValue() + " " + text);
    }
}