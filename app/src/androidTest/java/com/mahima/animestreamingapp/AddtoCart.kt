package com.mahima.animestreamingapp

import android.text.TextUtils.replace
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.mahima.animestreamingapp.ui.shop.PetItemFragment
import com.mahima.animestreamingapp.ui.shop.ProductDetailActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@LargeTest
@RunWith(JUnit4::class)
class AddtoCart {
    @Rule
    @JvmField
    val testRule = ActivityTestRule(DashboardActivity::class.java)

//    load fragment
    @Before
    fun setUp() {
        testRule.activity.supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, PetItemFragment())
            addToBackStack(null)
            commit()
        }
    }

    @Test
    fun addtocart() {

//        Espresso.onView(ViewMatchers.withChild())
//            .perform(ViewActions.click())
//
//        Thread.sleep(3000)


    }


}