package com.example.buildmypc.ui.newsfeed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.buildmypc.databinding.FragmentNewsfeedBinding;
import com.prof.rssparser.Channel;
import com.prof.rssparser.OnTaskCompleted;
import com.prof.rssparser.Parser;

import org.jetbrains.annotations.NotNull;

import java.nio.charset.Charset;

import static com.example.buildmypc.databinding.FragmentNewsfeedBinding.inflate;

public class NewsfeedFragment extends Fragment {
	private FragmentNewsfeedBinding binding;

	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		NewsfeedViewModel newsfeedViewModel = new ViewModelProvider(this).get(NewsfeedViewModel.class);
		binding = inflate(inflater, container, false);
		View root = binding.getRoot();
		final TextView textView = binding.textSlideshow;
		newsfeedViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
		// TODO parsing newsfeeds
		Parser parser = new Parser.Builder().charset(Charset.forName("ISO-8859-7")).build();
		parser.onFinish(new OnTaskCompleted() {
			//what to do when the parsing is done
			@Override
			public void onTaskCompleted(@NotNull Channel channel) {
				// Use the channel info
			}
			//what to do in case of error
			@Override
			public void onError(Exception e) {
				// Handle the exception
			}
		});
		parser.execute("");
		// ending return
		return root;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}
}