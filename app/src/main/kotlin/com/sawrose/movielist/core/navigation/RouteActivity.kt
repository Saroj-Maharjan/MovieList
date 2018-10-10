package com.sawrose.movielist.core.navigation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sawrose.movielist.MovieApp
import com.sawrose.movielist.core.di.component.AppComponent
import javax.inject.Inject

class RouteActivity : AppCompatActivity(){

    private val appComponent: AppComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (application as MovieApp).appComponent
    }

    @Inject
    internal lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        navigator.showMain(this)
    }
}
