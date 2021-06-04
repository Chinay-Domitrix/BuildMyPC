package com.example.buildmypc.ui.newsfeed;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.buildmypc.databinding.FragmentNewsfeedBinding;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import static com.example.buildmypc.databinding.FragmentNewsfeedBinding.inflate;

public class NewsfeedFragment extends Fragment {
	private FragmentNewsfeedBinding binding;
	static final AtomicReference<ArrayList<Article>> finalArticleList = new AtomicReference<>(new ArrayList<>());

	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		NewsfeedViewModel newsfeedViewModel = new ViewModelProvider(this).get(NewsfeedViewModel.class);
		binding = inflate(inflater, container, false);
		View root = binding.getRoot();
		((Activity) (root.getContext())).requestPermissions(new String[]{Manifest.permission.INTERNET}, 0);
		TextView textView = binding.textSlideshow;
		newsfeedViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
		Button button = binding.newsfeedFragButton;
		button.setOnClickListener(v -> {
			button.setClickable(false);
			button.setEnabled(false);
			new RSSAsyncTask().execute("https://www.techmeme.com/feed.xml", "Techmeme").execute("https://www.theverge.com/rss/index.xml", "The Verge").execute("https://www.wired.com/feed", "WIRED").execute("https://rss.nytimes.com/services/xml/rss/nyt/Technology.xml\n", "NYTimes").execute("https://www.engadget.com/rss.xml", "Engadget");
		});
		return root;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}
}