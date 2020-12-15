package com.example.Proyeto_DAM2_Tienda_Qr.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue( "This is home fragment" );
    }

    public LiveData<String> getText() {
        return mText;
    }
}