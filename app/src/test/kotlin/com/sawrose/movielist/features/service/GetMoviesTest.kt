package com.sawrose.movielist.features.service

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.sawrose.movielist.UnitTest
import com.sawrose.movielist.core.functional.Either.Right
import com.sawrose.movielist.core.interactor.UseCase
import com.sawrose.movielist.features.model.Movie
import com.sawrose.movielist.features.movies.MoviesRepository
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class GetMoviesTest : UnitTest() {

    private lateinit var getMovies: GetMovies

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Before
    fun setUp() {
        getMovies = GetMovies(moviesRepository)
        given { moviesRepository.movies() }.willReturn(Right(listOf(Movie.empty())))
    }

    @Test
    fun `should get data from repository`() {
        runBlocking { getMovies.run(UseCase.None()) }

        verify(moviesRepository).movies()
        verifyNoMoreInteractions(moviesRepository)
    }
}