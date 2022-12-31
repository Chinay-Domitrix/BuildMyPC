package com.example.buildmypc.ui.newsfeed

import android.Manifest.permission.INTERNET
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.net.Uri.parse
import android.os.Bundle
import android.view.LayoutInflater
import android.view.LayoutInflater.from
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent.Builder
import androidx.core.content.res.ResourcesCompat.getDrawable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.buildmypc.R.drawable.*
import com.example.buildmypc.R.id.*
import com.example.buildmypc.R.layout.layout_newsfeed_list
import com.example.buildmypc.databinding.FragmentNewsfeedBinding
import com.example.buildmypc.databinding.FragmentNewsfeedBinding.inflate
import com.example.buildmypc.ui.newsfeed.Article.OnDataSendToActivity
import com.example.buildmypc.ui.newsfeed.NewsfeedFragment.RecyclerViewAdapter.RecyclerViewHolder
import java.util.concurrent.atomic.AtomicReference

class NewsfeedFragment : Fragment(), OnDataSendToActivity {
	private var binding: FragmentNewsfeedBinding? = null
	private var fragmentAdapter: RecyclerViewAdapter? = null
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		// jank testing of finalArticleList actually being used in the RecyclerView
		if (finalArticleList == null) finalArticleList.set(ArrayList())
		binding = inflate(inflater, container, false)
		val root: View = binding!!.root
		(root.context as Activity).requestPermissions(arrayOf(INTERNET), 0)
		val recyclerView = binding!!.newsfeedRecyclerView
		if (finalArticleList.get().size == 0) { // start the chain!
			val logos = arrayOf(
				getDrawable(resources, verge, requireActivity().theme),
				getDrawable(resources, wired, requireActivity().theme),
				getDrawable(resources, nytimes, requireActivity().theme),
				getDrawable(resources, engadget, requireActivity().theme)
			)
			RSSAsyncTask(logos, this).execute("https://www.theverge.com/rss/index.xml", "The Verge")
		}
		fragmentAdapter = RecyclerViewAdapter(context, finalArticleList.get())
		recyclerView.adapter = fragmentAdapter
		recyclerView.layoutManager = LinearLayoutManager(context)
		return root
	}

	override fun onDestroyView() {
		super.onDestroyView()
		binding = null
	}

	override fun refreshList() {
		fragmentAdapter!!.notifyDataSetChanged()
	}

	class RecyclerViewAdapter(
		var parentContext: Context?,
		var articleViewList: ArrayList<Article>
	) : Adapter<RecyclerViewHolder>() {
		override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = // inflate xml here
			RecyclerViewHolder(from(parentContext).inflate(layout_newsfeed_list, parent, false))

		@SuppressLint("SetTextI18n")
		override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
			// the method where info is set for EACH individual layout element for each list entry
			val currentEntry = articleViewList[position]
			val onClickListener = OnClickListener {
				val builder = Builder()
				val customTabsIntent = builder.build()
				customTabsIntent.launchUrl(parentContext!!, parse(currentEntry.originURL))
			}
			holder.image.setImageDrawable(currentEntry.image)
			holder.title.text = currentEntry.heading
			holder.desc.text = "by ${currentEntry.author}"
			holder.dateAndPublisher.text = "Published at " + currentEntry.dateStr
			holder.image.setOnClickListener(onClickListener)
			holder.title.setOnClickListener(onClickListener)
			holder.desc.setOnClickListener(onClickListener)
			holder.dateAndPublisher.setOnClickListener(onClickListener)
		}

		override fun getItemCount() = articleViewList.size

		class RecyclerViewHolder(itemView: View) : ViewHolder(itemView) {
			var image: ImageView = itemView.findViewById(newslist_imageView)
			var title: TextView = itemView.findViewById(newslist_titleTextView)
			var desc: TextView = itemView.findViewById(newslist_descTextView)
			var dateAndPublisher: TextView = itemView.findViewById(newslist_publisherTextView)
		}
	}

	companion object {
		@JvmField
		val finalArticleList: AtomicReference<ArrayList<Article>> = AtomicReference(ArrayList())
	}
}