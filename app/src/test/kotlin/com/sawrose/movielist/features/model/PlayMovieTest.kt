package com.sawrose.movielist.features.model

import com.nhaarman.mockito_kotlin.verify
import com.sawrose.movielist.AndroidTest
import com.sawrose.movielist.core.navigation.Navigator
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class PlayMovieTest : AndroidTest() {
    private val VIDEO_URL = "https://www.youtube.com/watch?v=fernando"

    @Mock
    private lateinit var navigator: Navigator
    private val context = context()

    private lateinit var playMovie: PlayMovie

    @Before
    fun setUp() {
        playMovie = PlayMovie(context, navigator)
    }

    @Test
    fun `hould play movie trailer`() {
        val params = PlayMovie.Params(VIDEO_URL)

        playMovie(params)

        verify(navigator).openVideo(context, VIDEO_URL)
    }
}