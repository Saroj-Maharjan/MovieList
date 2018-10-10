package com.sawrose.movielist.features.movies

import android.arch.lifecycle.MutableLiveData
import com.sawrose.movielist.core.platform.BaseViewModel
import com.sawrose.movielist.features.model.MovieDetails
import com.sawrose.movielist.features.model.MovieDetailsView
import com.sawrose.movielist.features.model.PlayMovie
import com.sawrose.movielist.features.service.GetMovieDetails
import com.sawrose.movielist.features.service.GetMovieDetails.Params
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
        private val getMovieDetails: GetMovieDetails,
        private val playMovie: PlayMovie
) : BaseViewModel() {

    var movieDetails: MutableLiveData<MovieDetailsView> = MutableLiveData()

    fun loadMovieDetails(movieId: Int) =
            getMovieDetails(Params(movieId)) { it.either(::handleFailure, ::handleMovieDetails) }

    fun playMovie(url: String) = playMovie(PlayMovie.Params(url))

    private fun handleMovieDetails(movie: MovieDetails) {
        this.movieDetails.value = MovieDetailsView(movie.id, movie.title, movie.poster,
                movie.summary, movie.cast, movie.director, movie.year, movie.trailer)
    }
}