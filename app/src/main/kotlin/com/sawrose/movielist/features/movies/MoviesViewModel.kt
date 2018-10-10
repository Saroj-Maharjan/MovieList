package com.sawrose.movielist.features.movies

import android.arch.lifecycle.MutableLiveData
import com.sawrose.movielist.core.interactor.UseCase.None
import com.sawrose.movielist.core.platform.BaseViewModel
import com.sawrose.movielist.features.model.Movie
import com.sawrose.movielist.features.model.MovieView
import com.sawrose.movielist.features.service.GetMovies
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
        private val getMovies: GetMovies
) : BaseViewModel() {

    var movies: MutableLiveData<List<MovieView>> = MutableLiveData()

    fun loadMovies() = getMovies(None()) { it.either(::handleFailure, ::handleMovieList) }

    private fun handleMovieList(movies: List<Movie>) {
        this.movies.value = movies.map { MovieView(it.id, it.poster) }
    }
}