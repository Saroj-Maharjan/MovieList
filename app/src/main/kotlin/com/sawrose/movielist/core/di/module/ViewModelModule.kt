package com.sawrose.movielist.core.di.module

import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Suppress("unused")
@Module
abstract class ViewModelModule {

//    @Binds
//    @IntoMap
//    @ViewModelKey(SplashViewModel::class)
//    abstract fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(LoginViewModel::class)
//    abstract fun bindUserViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}