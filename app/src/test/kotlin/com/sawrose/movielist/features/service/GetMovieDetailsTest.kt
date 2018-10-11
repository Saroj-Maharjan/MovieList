package com.sawrose.movielist.features.service

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.sawrose.movielist.UnitTest
import com.sawrose.movielist.core.functional.Either.Right
import com.sawrose.movielist.features.model.MovieDetails
import com.sawrose.movielist.features.movies.MoviesRepository
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class GetMovieDetailsTest : UnitTest() {

    private val MOVIE_ID = 1

    private lateinit var getMovieDetails: GetMovieDetails

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Before
    fun setUp() {
        getMovieDetails = GetMovieDetails(moviesRepository)
        given(moviesRepository.movieDetail(MOVIE_ID)).willReturn(Right(MovieDetails.empty()))
    }

    @Test
    fun `should get data from repository`() {
        runBlocking { getMovieDetails.run(GetMovieDetails.Params(MOVIE_ID)) }

        verify(moviesRepository).movieDetail(MOVIE_ID)
        verifyNoMoreInteractions(moviesRepository)
    }

}