package com.example.buildmypc.ui.newsfeed;
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

import static com.example.buildmypc.databinding.FragmentNewsfeedBinding.inflate;

public class NewsfeedFragment extends Fragment {
	private FragmentNewsfeedBinding binding;

	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		NewsfeedViewModel newsfeedViewModel = new ViewModelProvider(this).get(NewsfeedViewModel.class);
		binding = inflate(inflater, container, false);
		View root = binding.getRoot();
		TextView textView = binding.textSlideshow;
		newsfeedViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
		Button button = binding.newsfeedFragButton;

		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				button.setClickable(false);
				button.setEnabled(false);
				ArrayList<Article> finalArticleList = new ArrayList<Article>();
				RSSAsyncTask backgroundTask = new RSSAsyncTask();
				finalArticleList.addAll(backgroundTask.execute("https://www.techmeme.com/feed.xml", "Techmeme"));
				finalArticleList.addAll(backgroundTask.execute("https://www.theverge.com/rss/index.xml", "The Verge"));
				finalArticleList.addAll(backgroundTask.execute("https://www.wired.com/feed", "WIRED"));
				finalArticleList.addAll(backgroundTask.execute("https://rss.nytimes.com/services/xml/rss/nyt/Technology.xml\n", "NYTimes"));
				finalArticleList.addAll(backgroundTask.execute("https://www.engadget.com/rss.xml", "Engadget"));
			}
		});

//		// TODO ---------- parsing newsfeeds
//
//		ArrayList<Article> finalArticleList = new ArrayList<Article>();
//		RSSAsyncTask backgroundTask = new RSSAsyncTask();
//		finalArticleList.addAll(backgroundTask.doInBackground("https://www.techmeme.com/feed.xml", "Techmeme"));
//		finalArticleList.addAll(backgroundTask.doInBackground("https://www.theverge.com/rss/index.xml", "The Verge"));
//		finalArticleList.addAll(backgroundTask.doInBackground("https://www.wired.com/feed", "WIRED"));
//		finalArticleList.addAll(backgroundTask.doInBackground("https://rss.nytimes.com/services/xml/rss/nyt/Technology.xml\n", "NYTimes"));
//		finalArticleList.addAll(backgroundTask.doInBackground("https://www.engadget.com/rss.xml", "Engadget"));


		// ending return
		return root;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}
}