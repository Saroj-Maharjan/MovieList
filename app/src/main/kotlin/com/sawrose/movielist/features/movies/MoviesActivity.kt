package com.sawrose.movielist.features.movies

import android.content.Context
import android.content.Intent
import com.sawrose.movielist.core.platform.BaseActivity
import com.sawrose.movielist.core.platform.BaseFragment

class MoviesActivity : BaseActivity(){

    companion object {
        fun callingIntent(context: Context) = Intent(context, MoviesActivity::class.java)
    }

    override fun fragment(): BaseFragment = MoviesFragment()
}
