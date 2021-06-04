package com.example.buildmypc.ui.newsfeed;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buildmypc.R;
import com.example.buildmypc.databinding.FragmentNewsfeedBinding;

import org.jetbrains.annotations.NotNull;

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
//		TextView textView = binding.textSlideshow;
//		newsfeedViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
		Button button = binding.newsfeedFragButton;
		button.setOnClickListener(v -> {
			button.setClickable(false);
			button.setEnabled(false);
			new RSSAsyncTask().execute("https://www.techmeme.com/feed.xml", "Techmeme").execute("https://www.theverge.com/rss/index.xml", "The Verge").execute("https://www.wired.com/feed", "WIRED").execute("https://rss.nytimes.com/services/xml/rss/nyt/Technology.xml\n", "NYTimes").execute("https://www.engadget.com/rss.xml", "Engadget");
		});
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

	public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
		Context parentContext;
		ArrayList<Article> articleViewList;

		public RecyclerViewAdapter(Context c, ArrayList<Article> arr) {
			parentContext = c;
			articleViewList = arr;
		}

		@NonNull
		@Override
		public RecyclerViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
			return null;
		}

		@Override
		public void onBindViewHolder(@NonNull @NotNull RecyclerViewHolder holder, int position) {
			// the method were info is set for EACH individual layout element for each list entry
			Article currentEntry = articleViewList.get(position);
		}

		@Override
		public int getItemCount() {
			return 0;
		}

		public class RecyclerViewHolder extends RecyclerView.ViewHolder {
			ImageView image;
			TextView title;
			TextView desc;
			TextView dateAndPublisher;

			public RecyclerViewHolder(@NonNull View itemView) {
				super(itemView);
				// this is where the findViewById stuff goes for each element, and only the findViewByID
				image = itemView.findViewById(R.id.newslist_imageView);
				title = itemView.findViewById(R.id.newslist_titleTextView);
				desc = itemView.findViewById(R.id.newslist_descTextView);
				dateAndPublisher = itemView.findViewById(R.id.newslist_publisherTextView);
			}
		}
	}
}