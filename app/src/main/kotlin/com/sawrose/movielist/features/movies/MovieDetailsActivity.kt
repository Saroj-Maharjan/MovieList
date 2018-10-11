package com.sawrose.movielist.features.movies

import android.content.Context
import android.content.Intent
import com.sawrose.movielist.core.platform.BaseActivity
import com.sawrose.movielist.features.model.MovieView

class MovieDetailsActivity : BaseActivity() {

    companion object {
        private const val INTENT_EXTRA_PARAM_MOVIE = "com.fernandocejas.INTENT_PARAM_MOVIE"

        fun callingIntent(context: Context, movie: MovieView): Intent {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(INTENT_EXTRA_PARAM_MOVIE, movie)
            return intent
        }
    }

    override fun fragment() = MovieDetailsFragment.forMovie(
            intent.getParcelableExtra(INTENT_EXTRA_PARAM_MOVIE))
}