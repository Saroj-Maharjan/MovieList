package com.sawrose.movielist.features.login

import android.content.Context
import android.content.Intent
import com.sawrose.movielist.core.platform.BaseActivity
import com.sawrose.movielist.core.platform.BaseFragment

class LoginActivity : BaseActivity() {

    companion object {
        fun CallingIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }

    override fun fragment(): BaseFragment = LoginFragment()
}