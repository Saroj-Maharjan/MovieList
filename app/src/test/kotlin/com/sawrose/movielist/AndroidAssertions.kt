@file:JvmName("AndroidAssertions")
@file:JvmMultifileClass

package com.sawrose.movielist

import android.support.v7.app.AppCompatActivity
import org.amshove.kluent.shouldEqual
import org.robolectric.Robolectric
import org.robolectric.Shadows
import kotlin.reflect.KClass

infix fun KClass<out AppCompatActivity>.`shouldNavigateTo`(
        nextActivity: KClass<out AppCompatActivity>) = {
    val originalActivity = Robolectric.buildActivity(this.java).get()
    val shadowActivity = Shadows.shadowOf(originalActivity)
    val nextIntent = shadowActivity.peekNextStartedActivity()

    nextIntent.component.className shouldEqual nextActivity.java.canonicalName
}