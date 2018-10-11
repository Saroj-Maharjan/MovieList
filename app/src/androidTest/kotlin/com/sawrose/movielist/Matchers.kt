package com.sawrose.movielist

import android.app.Activity
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE
import android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText

class Matchers {

    fun <T : Activity> nextOpenActivityIs(clazz: Class<T>) {
        intended(IntentMatchers.hasComponent(clazz.name))
    }

    fun viewIsInVisibleAndContainText(@StringRes stringRes: Int) {
        onView(withText(stringRes)).check(matches(withEffectiveVisibility(VISIBLE)))
    }

    fun viewContainerText(@IdRes viewId: Int, @StringRes stringRes: Int) {
        onView(withId(viewId)).check(matches(withText(stringRes)))
    }
}