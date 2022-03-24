package com.mahima.animestreamingapp

import android.app.Activity
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import kotlinx.coroutines.withContext
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@LargeTest
@RunWith(JUnit4::class)
class InstrumentedTest {

    @Rule
    @JvmField
    val testRule = ActivityScenarioRule(LoginActivity::class.java)

//    test function
    @Test
    fun checkLogin(){
        onView(withId(R.id.etusername))
            .perform(typeText("test2@email.com"))
        onView(ViewMatchers.withId(R.id.etpassword)).perform(ViewActions.scrollTo())

        onView(withId(R.id.etpassword))
        .perform(typeText("test123"))

        closeSoftKeyboard()
    onView(ViewMatchers.withId(R.id.etpassword)).perform(ViewActions.scrollTo())

        onView(withId(R.id.btnlogin))
            .perform(click())

        onView(withText("JoJo")).check(matches(isDisplayed()))
        Thread.sleep(1000)

}
}