package com.moonpi.swiftnotes.pages

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import com.moonpi.swiftnotes.R
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers.allOf

class EditPage {
    fun checkHintTitle() {
        onView(CoreMatchers.allOf(withId(R.id.titleEdit), isDisplayed())).check(matches(withHint("Title")))
    }

    fun checkHintNote() {
        onView(CoreMatchers.allOf(withId(R.id.bodyEdit), isDisplayed())).check(matches(withHint("Note")))
    }

    fun exitNote() {
        onView(allOf(withParent(withId(R.id.toolbarEdit)), isClickable())).perform(click())
    }

    fun checkDialogSaveChanges() {
        onView(withId(android.R.id.message)).check(matches(withText("Save changes?")))
        onView(allOf(withId(android.R.id.button1), isClickable())).check(matches(withText("Yes")))
        onView(allOf(withId(android.R.id.button2), isClickable())).check(matches(withText("No")))
    }

    fun clickYesInSaveChangesDialog() {
        onView(withId(android.R.id.button1)).perform(click())
    }

    fun clickNoinSaveChangesDialog() {
        onView(withId(android.R.id.button2)).perform(click())
    }


    fun inputTextTitle(text: String) {
        onView(allOf(withId(R.id.titleEdit), isDisplayed())).perform(typeText(text))
    }

    fun inputTextNote(text: String) {
        onView(allOf(withId(R.id.bodyEdit), isDisplayed())).perform(click(), typeText(text))
    }

    fun checkTextTitle(text: String) {
        onView(allOf(withId(R.id.titleEdit), isDisplayed())).check(matches(withText(text)))
    }

    fun checkTextNote(text: String) {
        onView(allOf(withId(R.id.bodyEdit), isDisplayed())).check(matches(withText(text)))
    }

    fun pressMenu() {
        onView(allOf(withContentDescription("Ещё"), isClickable())).perform(click())
    }


    fun checkMenu(text: String) {
        onView(allOf(withId(R.id.title), withText(text))).check(matches(isDisplayed()))
    }
}