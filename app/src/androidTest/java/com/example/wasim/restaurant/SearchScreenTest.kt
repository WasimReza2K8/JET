package com.example.wasim.restaurant

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.core.R
import com.example.core.ui.R.string
import com.example.core.ui.R.string.restaurant_list
import com.example.wasim.MainActivity
import com.example.wasim.restaurant.FakeRestaurantRepository.ReturnType.NetworkException
import com.example.wasim.restaurant.FakeRestaurantRepository.ReturnType.Valid
import com.jet.feature.restaurant.di.RestaurantDomainModule
import com.jet.restaurant.domain.repository.RestaurantRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Timer
import javax.inject.Inject
import kotlin.concurrent.schedule

@HiltAndroidTest
@MediumTest
@UninstallModules(RestaurantDomainModule::class)
@RunWith(AndroidJUnit4::class)
class SearchScreenTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val androidComposeTestRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var fakeRepository: RestaurantRepository

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun given_valid_response_when_activity_opened_list_shown() {
        (fakeRepository as? FakeRestaurantRepository)?.setReturnType(Valid)

        androidComposeTestRule.apply {
            val text = activity.getString(string.search_restaurant)
            onNodeWithContentDescription("Search Icon").performClick()
            onNodeWithText(text).performTextInput("de")
            AsyncTimer.start()
            waitUntil(
                condition = { AsyncTimer.expired },
                timeoutMillis = 1000
            )
            onNodeWithTag(activity.getString(restaurant_list))
                .onChildren()
                .assertCountEquals(2)
        }
    }

    @Test
    fun given_network_error_response_when_activity_opened_snackBar_shown() {
        (fakeRepository as? FakeRestaurantRepository)?.setReturnType(NetworkException)

        androidComposeTestRule.apply {
            val searchText = activity.getString(string.search_restaurant)
            onNodeWithContentDescription("Search Icon").performClick()
            onNodeWithText(searchText).performTextInput("de")
            onNodeWithTag(activity.getString(restaurant_list))
                .onChildren()
                .assertCountEquals(0)
            val text = activity.getString(R.string.network_error)
            onNodeWithText(text)
        }
    }

    @Test
    fun given_unknown_error_response_when_activity_opened_snackBar_shown() {
        (fakeRepository as? FakeRestaurantRepository)?.setReturnType(NetworkException)

        androidComposeTestRule.apply {
            val searchText = activity.getString(string.search_restaurant)
            onNodeWithContentDescription("Search Icon").performClick()
            onNodeWithText(searchText).performTextInput("de")
            onNodeWithTag(activity.getString(restaurant_list))
                .onChildren()
                .assertCountEquals(0)
            val text = activity.getString(R.string.unknown_error)
            onNodeWithText(text)
        }
    }
}

object AsyncTimer {
    var expired = false
        private set

    fun start(delay: Long = 350) {
        expired = false
        Timer().schedule(delay) {
            expired = true
        }
    }
}