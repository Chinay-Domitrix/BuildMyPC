package com.example.buildmypc.ui.build;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BuildViewModel extends ViewModel {
	private final MutableLiveData<String> mText;

	public BuildViewModel() {
		mText = new MutableLiveData<>();
		mText.setValue("This is home fragment");
	}
	public LiveData<String> getText() {
		return mText;
	}
}