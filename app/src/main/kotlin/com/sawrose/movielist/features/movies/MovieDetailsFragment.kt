package com.sawrose.movielist.features.movies

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.view.View
import com.sawrose.movielist.R
import com.sawrose.movielist.core.exception.Failure
import com.sawrose.movielist.core.exception.Failure.NetworkConnection
import com.sawrose.movielist.core.exception.Failure.ServerError
import com.sawrose.movielist.core.extension.close
import com.sawrose.movielist.core.extension.failure
import com.sawrose.movielist.core.extension.isVisible
import com.sawrose.movielist.core.extension.loadFromUrl
import com.sawrose.movielist.core.extension.loadUrlAndPostponeEnterTransition
import com.sawrose.movielist.core.extension.observe
import com.sawrose.movielist.core.extension.viewModel
import com.sawrose.movielist.core.platform.BaseFragment
import com.sawrose.movielist.features.animate.MovieDetailsAnimator
import com.sawrose.movielist.features.exceptions.MovieFailure.NonExistentMovie
import com.sawrose.movielist.features.model.MovieDetailsView
import com.sawrose.movielist.features.model.MovieView
import kotlinx.android.synthetic.main.fragment_movie_details.movieCast
import kotlinx.android.synthetic.main.fragment_movie_details.movieDetails
import kotlinx.android.synthetic.main.fragment_movie_details.movieDirector
import kotlinx.android.synthetic.main.fragment_movie_details.moviePlay
import kotlinx.android.synthetic.main.fragment_movie_details.moviePoster
import kotlinx.android.synthetic.main.fragment_movie_details.movieSummary
import kotlinx.android.synthetic.main.fragment_movie_details.movieYear
import kotlinx.android.synthetic.main.fragment_movie_details.scrollView
import kotlinx.android.synthetic.main.toolbar.toolbar
import javax.inject.Inject

class MovieDetailsFragment : BaseFragment() {

    companion object {

        private const val PARAM_MOVIE = "param_movie"

        fun forMovie(movie: MovieView): MovieDetailsFragment {
            val movieDetailsFragment = MovieDetailsFragment()
            val argument = Bundle()
            argument.putParcelable(PARAM_MOVIE, movie)
            movieDetailsFragment.arguments = argument

            return movieDetailsFragment
        }
    }

    @Inject
    lateinit var movieDetailsAnimator: MovieDetailsAnimator

    private lateinit var movieDetailsViewModel: MovieDetailsViewModel

    override fun layoutId(): Int = R.layout.fragment_movie_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent.inject(this)

        activity?.let {
            movieDetailsAnimator.postponeEnterTransition(activity = it)
        }

        movieDetailsViewModel = viewModel(viewModelFactory) {
            observe(movieDetails, ::renderMovieDetails)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (firstTimeCreated(savedInstanceState)) {
            movieDetailsViewModel.loadMovieDetails((arguments?.get(PARAM_MOVIE) as MovieView).id)
        } else {
            movieDetailsAnimator.scaleUpView(moviePlay)
            movieDetailsAnimator.cancelTransition(moviePoster)
            moviePoster.loadFromUrl((arguments!![PARAM_MOVIE] as MovieView).poster)
        }
    }

    override fun onBackPressed() {
        movieDetailsAnimator.fadeInvisible(scrollView, movieDetails)
        if (moviePlay.isVisible()) {
            movieDetailsAnimator.scaleDownView(moviePlay)
        } else {
            if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
                movieDetailsAnimator.cancelTransition(moviePoster)
            }
        }
    }

    private fun renderMovieDetails(movie: MovieDetailsView?) {
        movie?.let {
            with(movie) {
                activity?.let {
                    moviePoster.loadUrlAndPostponeEnterTransition(poster, it)
                    it.toolbar.title = title
                }
                movieSummary.text = summary
                movieCast.text = cast
                movieDirector.text = director
                movieYear.text = year.toString()
                moviePlay.setOnClickListener { movieDetailsViewModel.playMovie(trailer) }
            }
        }

        movieDetailsAnimator.fadeVisible(scrollView, movieDetails)
        movieDetailsAnimator.scaleUpView(moviePlay)
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is NetworkConnection -> {
                notify(R.string.failure_network_connection); close()
            }
            is ServerError -> {
                notify(R.string.failure_server_error); close()
            }
            is NonExistentMovie -> {
                notify(R.string.failure_movie_non_existent); close()
            }
        }
    }
}