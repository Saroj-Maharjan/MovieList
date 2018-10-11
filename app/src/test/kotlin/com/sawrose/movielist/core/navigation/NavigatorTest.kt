package com.sawrose.movielist.core.navigation

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.sawrose.movielist.AndroidTest
import com.sawrose.movielist.features.login.Authenticator
import com.sawrose.movielist.features.login.LoginActivity
import com.sawrose.movielist.features.movies.MoviesActivity
import com.sawrose.movielist.shouldNavigateTo
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class NavigatorTest : AndroidTest() {
    private lateinit var navigator: Navigator

    @Mock
    private lateinit var authenticator: Authenticator

    @Before
    fun setUp() {
        navigator = Navigator(authenticator)
    }

    @Test
    fun `should forward to login screen`() {
        whenever(authenticator.userLoggedIn()).thenReturn(false)

        navigator.showMain(activityContext())

        verify(authenticator).userLoggedIn()
        RouteActivity::class shouldNavigateTo LoginActivity::class
    }

    @Test
    fun `should forward to movie screen`() {
        whenever(authenticator.userLoggedIn()).thenReturn(true)

        navigator.showMain(activityContext())

        verify(authenticator).userLoggedIn()
        RouteActivity::class shouldNavigateTo MoviesActivity::class
    }
}