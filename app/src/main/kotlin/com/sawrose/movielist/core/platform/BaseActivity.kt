package com.sawrose.movielist.core.platform

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sawrose.movielist.R.id
import com.sawrose.movielist.R.layout
import com.sawrose.movielist.core.extension.inTransaction
import kotlinx.android.synthetic.main.toolbar.toolbar

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_layout)
        setSupportActionBar(toolbar)
        addFragment(savedInstanceState)
    }

    override fun onBackPressed() {
        (supportFragmentManager.findFragmentById(id.fragmentContainer))
        super.onBackPressed()
    }

    private fun addFragment(savedInstanceState: Bundle?) =
            savedInstanceState ?: supportFragmentManager.inTransaction {
                add(
                        id.fragmentContainer, fragment())
            }

    abstract fun fragment(): BaseFragment
}