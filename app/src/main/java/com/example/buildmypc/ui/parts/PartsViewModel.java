package com.example.buildmypc.ui.parts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PartsViewModel extends ViewModel {
	private final MutableLiveData<String> mText;

	public PartsViewModel() {
		mText = new MutableLiveData<>();
		mText.setValue("This is gallery fragment");
	}

	public LiveData<String> getText() {
		return mText;
	}
}