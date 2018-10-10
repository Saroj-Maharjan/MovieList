package com.sawrose.movielist.core.di.component

import com.sawrose.movielist.MovieApp
import com.sawrose.movielist.core.di.module.AppModule
import com.sawrose.movielist.core.di.module.ViewModelModule
import com.sawrose.movielist.core.navigation.RouteActivity
import com.sawrose.movielist.features.movies.MovieDetailsFragment
import com.sawrose.movielist.features.movies.MoviesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    ViewModelModule::class
])
interface AppComponent {

    fun inject(application: MovieApp)
    fun inject(routeActivity: RouteActivity)

    fun inject(moviesFragment: MoviesFragment)
    fun inject(movieDetailsFragment: MovieDetailsFragment)
}
