package com.sawrose.movielist.features.movies

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.given
import com.sawrose.movielist.AndroidTest
import com.sawrose.movielist.core.functional.Either.Right
import com.sawrose.movielist.features.model.MovieDetails
import com.sawrose.movielist.features.model.PlayMovie
import com.sawrose.movielist.features.service.GetMovieDetails
import kotlinx.coroutines.experimental.runBlocking
import org.amshove.kluent.shouldEqualTo
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class MovieDetailsViewModelTest : AndroidTest() {

    private lateinit var moviesDetailsViewModel: MovieDetailsViewModel

    @Mock
    private lateinit var getMovieDetails: GetMovieDetails
    @Mock
    private lateinit var playMovie: PlayMovie

    @Before
    fun setUp() {
        moviesDetailsViewModel = MovieDetailsViewModel(getMovieDetails, playMovie)
    }

    @Test
    fun `loading movie detail show load liveData`() {
        val movieDetails = MovieDetails(0, "IronMan", "poster", "summary",
                "cast", "director", 2018, "trailer")

        given { runBlocking { getMovieDetails.run(eq(any())) } }.willReturn(Right(movieDetails))

        moviesDetailsViewModel.movieDetails.observeForever {
            with(it!!) {
                id shouldEqualTo 0
                title shouldEqualTo "IronMan"
                poster shouldEqualTo "poster"
                summary shouldEqualTo "summary"
                cast shouldEqualTo "cast"
                director shouldEqualTo "director"
                year shouldEqualTo 2018
                trailer shouldEqualTo "trailer"
            }
        }

        runBlocking { moviesDetailsViewModel.loadMovieDetails(0) }
    }
}