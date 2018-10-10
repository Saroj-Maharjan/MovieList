package com.sawrose.movielist.features.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.sawrose.movielist.R
import com.sawrose.movielist.core.extension.inflate
import com.sawrose.movielist.core.extension.loadFromUrl
import com.sawrose.movielist.core.navigation.Navigator
import com.sawrose.movielist.features.model.MovieView
import kotlinx.android.synthetic.main.row_movies.view.moviePoster
import javax.inject.Inject
import kotlin.properties.Delegates

class MoviesAdapter @Inject constructor() : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    internal var collection: List<MovieView> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    internal var clickListener: (MovieView, Navigator.Extras) -> Unit = { _, _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(parent.inflate(R.layout.row_movies))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(collection[position], clickListener)
    }

    override fun getItemCount(): Int = collection.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movieView: MovieView, clickListener: (MovieView, Navigator.Extras) -> Unit) {
            itemView.moviePoster.loadFromUrl(movieView.poster)
            itemView.setOnClickListener {
                clickListener(movieView, Navigator.Extras(itemView.moviePoster))
            }
        }
    }
}
