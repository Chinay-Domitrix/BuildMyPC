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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		NewsfeedViewModel that = (NewsfeedViewModel) o;

		return mText.equals(that.mText);
	}

	@Override
	public int hashCode() {
		return mText.hashCode();
	}
}