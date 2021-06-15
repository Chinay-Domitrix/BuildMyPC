package com.example.buildmypc.ui.newsfeed;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
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
import static android.net.Uri.parse;
import static android.view.LayoutInflater.from;
import static android.view.View.OnClickListener;
import static androidx.browser.customtabs.CustomTabsIntent.Builder;
import static androidx.core.content.res.ResourcesCompat.getDrawable;
import static com.example.buildmypc.R.drawable.engadget;
import static com.example.buildmypc.R.drawable.nytimes;
import static com.example.buildmypc.R.drawable.verge;
import static com.example.buildmypc.R.drawable.wired;
import static com.example.buildmypc.R.id.newslist_descTextView;
import static com.example.buildmypc.R.id.newslist_imageView;
import static com.example.buildmypc.R.id.newslist_publisherTextView;
import static com.example.buildmypc.R.id.newslist_titleTextView;
import static com.example.buildmypc.R.layout.layout_newsfeed_list;
import static com.example.buildmypc.databinding.FragmentNewsfeedBinding.inflate;
import static java.util.Locale.getDefault;

public class NewsfeedFragment extends Fragment implements Article.OnDataSendToActivity {
	static final AtomicReference<ArrayList<Article>> finalArticleList = new AtomicReference<>(new ArrayList<>());
	private FragmentNewsfeedBinding binding;
	private RecyclerViewAdapter fragmentAdapter;

	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// jank testing of finalArticleList actually being used in the RecyclerView
		if(finalArticleList == null) finalArticleList.set(new ArrayList<>());
		NewsfeedViewModel newsfeedViewModel = new ViewModelProvider(this).get(NewsfeedViewModel.class);
		binding = inflate(inflater, container, false);
		View root = binding.getRoot();
		((Activity) (root.getContext())).requestPermissions(new String[]{INTERNET}, 0);
		RecyclerView recyclerView = binding.newsfeedRecyclerView;

		if(finalArticleList.get().size() == 0){ // start the chain!
			Drawable[] logos = {
					getDrawable(getResources(), verge, requireActivity().getTheme()),
					getDrawable(getResources(), wired, requireActivity().getTheme()),
					getDrawable(getResources(), nytimes, requireActivity().getTheme()),
					getDrawable(getResources(), engadget, requireActivity().getTheme()),
			};
			new RSSAsyncTask(logos, this).execute("https://www.theverge.com/rss/index.xml", "The Verge");
		}

		fragmentAdapter = new RecyclerViewAdapter(getContext(), finalArticleList.get());
		recyclerView.setAdapter(fragmentAdapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		return root;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}

	@Override
	public void refreshList() {
		fragmentAdapter.notifyDataSetChanged();
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
			OnClickListener onClickListener = v -> {
				Builder builder = new Builder();
				CustomTabsIntent customTabsIntent = builder.build();
				customTabsIntent.launchUrl(parentContext, parse(currentEntry.getOriginURL()));
			};
			holder.image.setImageDrawable(currentEntry.getImage());
			holder.title.setText(currentEntry.getHeading());
			holder.desc.setText("by " + currentEntry.getAuthor());
			holder.dateAndPublisher.setText("Published at " + currentEntry.getDateStr());
			holder.image.setOnClickListener(onClickListener);
			holder.title.setOnClickListener(onClickListener);
			holder.desc.setOnClickListener(onClickListener);
			holder.dateAndPublisher.setOnClickListener(onClickListener);
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