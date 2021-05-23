package com.example.buildmypc.ui.newsfeed;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewsfeedViewModel extends ViewModel {
	private final MutableLiveData<String> mText;
	public NewsfeedViewModel() {
		mText = new MutableLiveData<>();
		mText.setValue("This is slideshow fragment");
	}
	public LiveData<String> getText() {
		return mText;
	}
}