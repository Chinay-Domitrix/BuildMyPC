package com.example.buildmypc.ui.build;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Objects;

public class BuildViewModel extends ViewModel {
	private final MutableLiveData<String> mText;

	public BuildViewModel() {
		mText = new MutableLiveData<>();
		mText.setValue("This is home fragment");
	}

	public LiveData<String> getText() {
		return mText;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		BuildViewModel that = (BuildViewModel) o;

		return Objects.equals(mText, that.mText);
	}

	@Override
	public int hashCode() {
		return mText != null ? mText.hashCode() : 0;
	}
}