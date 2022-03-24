package com.mahima.animestreamingapp

import android.text.TextUtils.replace
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.mahima.animestreamingapp.ui.shop.PetItemFragment
import com.mahima.animestreamingapp.ui.shop.ProductDetailActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.EnumSet.allOf

@LargeTest
@RunWith(AndroidJUnit4::class)
class AddFavorites {
    @Rule
    @JvmField
    val testRule = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun addtofavs() {

//        username
        val emailEditText = onView(
            Matchers.allOf(
                withId(R.id.etusername),
                ViewMatchers.isDisplayed()
            )
        )

        emailEditText.perform(
            ViewActions.replaceText("test2@email.com"),
            ViewActions.closeSoftKeyboard()
        )

//        password entry
        val passwordEditText = onView(
            Matchers.allOf(
                withId(R.id.etpassword),
                ViewMatchers.isDisplayed()
            )
        )

        passwordEditText.perform(
            ViewActions.replaceText("test123"),
            ViewActions.closeSoftKeyboard()
        )


        val LoginButton = onView(
            withId(R.id.btnlogin)

        )
        LoginButton.perform(ViewActions.click())
        Thread.sleep(2000)
        val bottomNavigationItemView = onView(
            Matchers.allOf(
                withId(R.id.navigation_shop), ViewMatchers.withContentDescription("Shop"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_view),
                        0
                    ),
                    1
                ),
                ViewMatchers.isDisplayed()
            )
        )
        bottomNavigationItemView.perform(ViewActions.click())

        Thread.sleep(3000)
        val recyclerView = onView(
            Matchers.allOf(
                withId(R.id.recyclerview),
                childAtPosition(
                    withId(R.id.cardproduct),
                    0
                )
            )
        )
        recyclerView.perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>
                (0, ViewActions.click()))

        Thread.sleep(3000)
        val textView = onView(
            Matchers.allOf(
                withId(R.id.tvproduct_name), ViewMatchers.withText("Dog Food"),
                ViewMatchers.withParent(ViewMatchers.withParent(withId(R.id.productdetail))),
                ViewMatchers.isDisplayed()
            )
        )
        isDisplayed()

        ViewMatchers.isDisplayed()
        Thread.sleep(3000)

        val CartButton = onView(
            withId(R.id.btnaddtocart)
        )
        onView(ViewMatchers.withId(R.id.btnaddtocart)).perform(ViewActions.scrollTo())

        val favsButon = onView(
            withId(R.id.btnaddtofavs)
        )
        onView(ViewMatchers.withId(R.id.btnaddtofavs)).perform(ViewActions.scrollTo())

        favsButon.perform(ViewActions.click())
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