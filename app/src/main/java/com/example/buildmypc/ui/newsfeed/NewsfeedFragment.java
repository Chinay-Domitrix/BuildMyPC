package com.example.buildmypc.ui.newsfeed;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.buildmypc.databinding.FragmentNewsfeedBinding;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

import static android.Manifest.permission.INTERNET;
import static android.view.LayoutInflater.from;
import static androidx.core.content.res.ResourcesCompat.getDrawable;
import static com.example.buildmypc.R.drawable.nytimes;
import static com.example.buildmypc.R.id.newslist_descTextView;
import static com.example.buildmypc.R.id.newslist_imageView;
import static com.example.buildmypc.R.id.newslist_publisherTextView;
import static com.example.buildmypc.R.id.newslist_titleTextView;
import static com.example.buildmypc.R.layout.layout_newsfeed_list;
import static com.example.buildmypc.databinding.FragmentNewsfeedBinding.inflate;
import static java.util.Locale.getDefault;

public class NewsfeedFragment extends Fragment {
	static final AtomicReference<ArrayList<Article>> finalArticleList = new AtomicReference<>(new ArrayList<>());
	private FragmentNewsfeedBinding binding;

	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// jank testing of finalArticleList actually being used in the RecyclerView
		ArrayList<Article> temp = finalArticleList.get();
		temp.add(new Article("The U.S. added 559,000 jobs in May",
				"Many employers report having trouble finding applicants. Economists say the labor market may simply need time to get sorted out.",
				/*new URL(*/"https://www.nytimes.com/2021/06/04/business/economy/jobs-report-may-2021.html"/*)*/,
				"NYTimes",
				getDrawable(getResources(), nytimes, requireActivity().getTheme()),
				new Date(),
				new SimpleDateFormat("yyyy-MM-dd", getDefault())));
		finalArticleList.set(temp);
		NewsfeedViewModel newsfeedViewModel = new ViewModelProvider(this).get(NewsfeedViewModel.class);
		binding = inflate(inflater, container, false);
		View root = binding.getRoot();
		((Activity) (root.getContext())).requestPermissions(new String[]{INTERNET}, 0);
		RecyclerView recyclerView = binding.newsfeedRecyclerView;
		Button button = binding.newsfeedFragButton;
		button.setOnClickListener(v -> {
			button.setClickable(false);
			button.setEnabled(false);
			new RSSAsyncTask().execute("https://www.techmeme.com/feed.xml", "Techmeme");
			new RSSAsyncTask().execute("https://www.theverge.com/rss/index.xml", "The Verge");
			new RSSAsyncTask().execute("https://www.wired.com/feed", "WIRED");
			new RSSAsyncTask().execute("https://rss.nytimes.com/services/xml/rss/nyt/Technology.xml", "NYTimes");
			new RSSAsyncTask().execute("https://www.engadget.com/rss.xml", "Engadget");
		});
		RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), finalArticleList.get());
		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		return root;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}

	public static class RecyclerViewAdapter extends Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
		Context parentContext;
		ArrayList<Article> articleViewList;

		public RecyclerViewAdapter(Context c, ArrayList<Article> arr) {
			parentContext = c;
			articleViewList = arr;
		}

		@NonNull
		@Override
		public RecyclerViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
			// inflate xml here
			View view = from(parentContext).inflate(layout_newsfeed_list, parent, false);
			RecyclerViewHolder holder = new RecyclerViewHolder(view);
			return holder;
		}

		@Override
		public void onBindViewHolder(@NonNull @NotNull RecyclerViewHolder holder, int position) {
			// the method where info is set for EACH individual layout element for each list entry
			Article currentEntry = articleViewList.get(position);
			holder.image.setImageDrawable(currentEntry.getImage());
			holder.title.setText(currentEntry.getHeading());
			holder.desc.setText(currentEntry.getDesc());
			holder.dateAndPublisher.setText(currentEntry.getPublisher() + " at " + currentEntry.getDate().toString());
		}

		@Override
		public int getItemCount() {
			return articleViewList.size();
		}

		public static class RecyclerViewHolder extends ViewHolder {
			ImageView image;
			TextView title;
			TextView desc;
			TextView dateAndPublisher;

			public RecyclerViewHolder(@NonNull View itemView) {
				super(itemView);
				// this is where the findViewById stuff goes for each element, and only the findViewByID
				image = itemView.findViewById(newslist_imageView);
				title = itemView.findViewById(newslist_titleTextView);
				desc = itemView.findViewById(newslist_descTextView);
				dateAndPublisher = itemView.findViewById(newslist_publisherTextView);
			}
		}
	}
}