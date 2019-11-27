package com.moonpi.swiftnotes.pages

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import com.moonpi.swiftnotes.R
import junit.framework.Assert.fail
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers.allOf

class MainPage {
    fun pressMenu() {
        onView(allOf(withContentDescription("Ещё"), isClickable())).perform(click())
    }

    fun createNote() {
        onView(withId(R.id.newNote)).perform(click())

    }

    fun checkMainScreen() {
        onView(withId(R.id.toolbarMain)).check(matches(isDisplayed()))
    }

    fun checkTextTitleOnMain(text: String) {
        onView(withId(R.id.titleView)).check(matches(withText(text)))
    }

    fun checkTextNoteOnMain(text: String) {
        onView(withId(R.id.bodyView)).check(matches(withText(text)))
    }

    fun checkMenu(text: String) {
        onView(allOf(withId(R.id.title), withText(text))).check(matches(isDisplayed()))
    }

    fun checkDeleted(text: String) {
        try {
            onView(withId(R.id.titleView)).check(matches(withText(text)))
            fail()
        } catch (e: Exception) {

        }
    }

    fun deleteNote(text: String) {
        onView(allOf(withId(R.id.relativeLayout), withChild(withText(text)))).perform(longClick())
        onView(withContentDescription("Ещё")).perform(click())
        onView(allOf(withId(android.R.id.button1))).perform(click())
    }
}