package com.mahima.animestreamingapp

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.ViewPagerActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
@LargeTest
@RunWith(AndroidJUnit4::class)
class HireTest {
    @Rule
    @JvmField
    val testRule = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun hiretest() {

//        username
        val emailEditText = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.etusername),
                ViewMatchers.isDisplayed()
            )
        )

        emailEditText.perform(
            ViewActions.replaceText("owner1@gmail.com"),
            ViewActions.closeSoftKeyboard()
        )

//        password entry
        val passwordEditText = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.etpassword),
                ViewMatchers.isDisplayed()
            )
        )

        passwordEditText.perform(
            ViewActions.replaceText("owner"),
            ViewActions.closeSoftKeyboard()
        )

//      to log in
        val LoginButton = Espresso.onView(
            ViewMatchers.withId(R.id.btnlogin)

        )
        LoginButton.perform(ViewActions.click())
        Thread.sleep(2000)
//        click on shop icon
        val bottomNavigationItemView = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.navigation_shop),
                ViewMatchers.withContentDescription("Shop"),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withId(R.id.nav_view),
                        0
                    ),
                    1
                ),
                ViewMatchers.isDisplayed()
            )
        )
        bottomNavigationItemView.perform(ViewActions.click())
        Thread.sleep(3000)

//to swipe right for hiring in the viewpager
        val swipeforhire = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.container),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withId(R.id.viewpager),
                        2),
                0
                ),
            ViewMatchers.isDisplayed()
            )
        )
       swipeforhire.perform(ViewPagerActions.scrollRight())

//        click on hires
        val recyclerView = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.recyclerview),
                childAtPosition(
                    ViewMatchers.withId(R.id.cardproduct),
                    0
                )
            )
        )
        recyclerView.perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>
                (0, ViewActions.click())
        )

        Thread.sleep(3000)
        val textView = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.tvproduct_name), ViewMatchers.withText("Dog Food"),
                ViewMatchers.withParent(ViewMatchers.withParent(ViewMatchers.withId(R.id.productdetail))),
                ViewMatchers.isDisplayed()
            )
        )

        ViewMatchers.isDisplayed()
        Thread.sleep(3000)
        val CartButton = Espresso.onView(
            ViewMatchers.withId(R.id.btnaddtocart)
        )
        CartButton.perform(ViewActions.click())
        Thread.sleep(3000)

    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }


}