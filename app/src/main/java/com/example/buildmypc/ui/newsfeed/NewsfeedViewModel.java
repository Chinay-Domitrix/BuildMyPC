package com.example.buildmypc.ui.newsfeed;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewsfeedViewModel extends ViewModel {
	private final MutableLiveData<String> mText = new MutableLiveData<>();

	public NewsfeedViewModel() {
		mText.setValue("This is slideshow fragment");
	}

	public LiveData<String> getText() {
		return mText;
	}
}