package com.sawrose.movielist.features.movies

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.given
import com.sawrose.movielist.AndroidTest
import com.sawrose.movielist.core.functional.Either.Right
import com.sawrose.movielist.features.model.Movie
import com.sawrose.movielist.features.service.GetMovies
import kotlinx.coroutines.experimental.runBlocking
import org.amshove.kluent.shouldEqualTo
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class MoviesViewModelTest : AndroidTest() {
    private lateinit var moviesViewModel: MoviesViewModel

    @Mock
    private lateinit var getMovies: GetMovies

    @Before
    fun setup() {
        moviesViewModel = MoviesViewModel(getMovies)
    }

    @Test
    fun `loading movies should update live data`() {
        val moviesList = listOf(Movie(0, "IronMan"), Movie(1, "Batman"))
        given { runBlocking { getMovies.run(eq(any())) } }.willReturn(Right(moviesList))

        moviesViewModel.movies.observeForever {
            it!!.size shouldEqualTo 2
            it[0].id shouldEqualTo 0
            it[0].poster shouldEqualTo "IronMan"
            it[1].id shouldEqualTo 1
            it[1].poster shouldEqualTo "Batman"
        }

        runBlocking { moviesViewModel.loadMovies() }
    }
}