package com.sawrose.movielist

import android.app.Application
import com.sawrose.movielist.core.di.component.AppComponent
import com.sawrose.movielist.core.di.component.DaggerAppComponent
import com.sawrose.movielist.core.di.module.AppModule
import com.squareup.leakcanary.LeakCanary

class MovieApp : Application() {

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
        this.initializeLeakDetection()
    }

    private fun injectMembers() {
        appComponent.inject(this)
    }

    private fun initializeLeakDetection() {
        if (BuildConfig.DEBUG) LeakCanary.install(this)
    }

    val appComponent: AppComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }
}