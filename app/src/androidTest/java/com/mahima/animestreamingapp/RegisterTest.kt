package com.mahima.animestreamingapp

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@LargeTest
@RunWith(JUnit4::class)
class RegisterTest {

    @Rule
    @JvmField
    val testRule = ActivityScenarioRule(SignupActivity::class.java)

    //    test function
    @Test
    fun checkRegister() {
        onView(ViewMatchers.withId(R.id.etusername))
            .perform(ViewActions.typeText("test"))

        onView(ViewMatchers.withId(R.id.etemail))
            .perform(ViewActions.typeText("test2@email.com"))

        onView(ViewMatchers.withId(R.id.etpassword))
            .perform(ViewActions.typeText("test123"))

        onView(ViewMatchers.withId(R.id.etpassword)).perform(ViewActions.scrollTo())

        onView(ViewMatchers.withId(R.id.etconfirmpassword))
            .perform(scrollTo())
            .perform(click())

        onView(ViewMatchers.withId(R.id.etconfirmpassword))
            .perform(ViewActions.typeText("test123"))

        closeSoftKeyboard()

        onView(ViewMatchers.withId(R.id.btnsignup))
            .perform(ViewActions.click())

        onView(ViewMatchers.withText("JoJo"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Thread.sleep(1000)
    }
}