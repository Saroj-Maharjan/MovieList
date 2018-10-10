package com.sawrose.movielist.features.movies

import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.sawrose.movielist.R
import com.sawrose.movielist.core.exception.Failure
import com.sawrose.movielist.core.exception.Failure.NetworkConnection
import com.sawrose.movielist.core.exception.Failure.ServerError
import com.sawrose.movielist.core.extension.failure
import com.sawrose.movielist.core.extension.invisible
import com.sawrose.movielist.core.extension.observe
import com.sawrose.movielist.core.extension.viewModel
import com.sawrose.movielist.core.extension.visible
import com.sawrose.movielist.core.navigation.Navigator
import com.sawrose.movielist.core.platform.BaseFragment
import com.sawrose.movielist.features.adapter.MoviesAdapter
import com.sawrose.movielist.features.exceptions.MovieFailure.ListNotAvailable
import com.sawrose.movielist.features.model.MovieView
import kotlinx.android.synthetic.main.fragment_movies.emptyView
import kotlinx.android.synthetic.main.fragment_movies.movieList
import javax.inject.Inject

class MoviesFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator
    @Inject
    lateinit var moviesAdapter: MoviesAdapter

    private lateinit var moviesViewModel: MoviesViewModel

    override fun layoutId(): Int = R.layout.fragment_movies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        moviesViewModel = viewModel(viewModelFactory) {
            observe(movies, ::renderMoviesList)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        loadMovieList()
    }

    private fun initializeView() {
        movieList.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        movieList.adapter = moviesAdapter
        moviesAdapter.clickListener = { movie, navigationExtras ->
            //            navigator.showMain()
        }
    }

    private fun loadMovieList() {
        emptyView.invisible()
        movieList.visible()
        showProgress()
        moviesViewModel.loadMovies()
    }

    private fun renderMoviesList(movies: List<MovieView>?) {
        moviesAdapter.collection = movies.orEmpty()
        hideProgress()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is ServerError -> renderFailure(R.string.failure_server_error)
            is ListNotAvailable -> renderFailure(R.string.failure_movies_list_unavailable)
        }
    }

    private fun renderFailure(@StringRes message: Int) {
        movieList.invisible()
        emptyView.visible()
        hideProgress()
        notifyWithAction(message, R.string.action_refresh, ::loadMovieList)
    }
}


