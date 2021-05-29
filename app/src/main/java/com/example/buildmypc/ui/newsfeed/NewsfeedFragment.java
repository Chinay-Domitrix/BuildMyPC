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

import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

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
		try {
			new XMLReader() {
				@Override
				public boolean getFeature(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
					return false;
				}

				@Override
				public void setFeature(String name, boolean value) throws SAXNotRecognizedException, SAXNotSupportedException {

				}

				@Override
				public Object getProperty(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
					return null;
				}

				@Override
				public void setProperty(String name, Object value) throws SAXNotRecognizedException, SAXNotSupportedException {

				}

				@Override
				public void setEntityResolver(EntityResolver resolver) {

				}

				@Override
				public EntityResolver getEntityResolver() {
					return null;
				}

				@Override
				public void setDTDHandler(DTDHandler handler) {

				}

				@Override
				public DTDHandler getDTDHandler() {
					return null;
				}

				@Override
				public void setContentHandler(ContentHandler handler) {

				}

				@Override
				public ContentHandler getContentHandler() {
					return null;
				}

				@Override
				public void setErrorHandler(ErrorHandler handler) {

				}

				@Override
				public ErrorHandler getErrorHandler() {
					return null;
				}

				@Override
				public void parse(InputSource input) throws IOException, SAXException {

				}

				@Override
				public void parse(String systemId) throws IOException, SAXException {

				}
			}.parse(new URL("https://www.engadget.com/rss.xml").toURI().toString());
		} catch (IOException | SAXException | URISyntaxException e) {
			e.printStackTrace();
		}
		// ending return
		return root;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}
}