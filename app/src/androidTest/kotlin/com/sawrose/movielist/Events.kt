package com.sawrose.movielist

import android.support.annotation.StringRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.withId

class Events {
    fun clickOnView(@StringRes viewId: Int) {
        onView(withId(viewId)).perform(click())
    }
}