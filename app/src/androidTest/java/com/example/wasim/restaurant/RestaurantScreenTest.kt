package com.example.wasim.restaurant

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.core.R
import com.example.core.ui.R.string.restaurant_list
import com.example.wasim.MainActivity
import com.example.wasim.restaurant.FakeRestaurantRepository.ReturnType.NetworkException
import com.example.wasim.restaurant.FakeRestaurantRepository.ReturnType.Valid
import com.jet.feature.restaurant.R.string
import com.jet.feature.restaurant.di.RestaurantDomainModule
import com.jet.restaurant.domain.repository.RestaurantRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import restaurants
import javax.inject.Inject

@HiltAndroidTest
@MediumTest
@UninstallModules(RestaurantDomainModule::class)
@RunWith(AndroidJUnit4::class)
class RestaurantScreenTest {

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
            onNodeWithTag(activity.getString(restaurant_list))
                .onChildren()
                .assertCountEquals(restaurants.size)
        }
    }

    @Test
    fun given_valid_response_when_best_match_clicked_list_shown() {
        (fakeRepository as? FakeRestaurantRepository)?.setReturnType(Valid)

        androidComposeTestRule.apply {
            onNodeWithText(activity.getString(string.restaurant_best_match)).performClick()
            onNodeWithTag(activity.getString(restaurant_list))
                .onChildren()
                .assertCountEquals(restaurants.size)
        }
    }

    @Test
    fun given_valid_response_when_avg_rating_clicked_list_shown() {
        (fakeRepository as? FakeRestaurantRepository)?.setReturnType(Valid)

        androidComposeTestRule.apply {
            onNodeWithText(activity.getString(string.restaurant_rating_avg)).performClick()
            onNodeWithTag(activity.getString(restaurant_list))
                .onChildren()
                .assertCountEquals(restaurants.size)
        }
    }

    @Test
    fun given_valid_response_when_distance_clicked_list_shown() {
        (fakeRepository as? FakeRestaurantRepository)?.setReturnType(Valid)

        androidComposeTestRule.apply {
            onNodeWithText(activity.getString(string.restaurant_distance)).performClick()
            onNodeWithTag(activity.getString(restaurant_list))
                .onChildren()
                .assertCountEquals(restaurants.size)
        }
    }

    @Test
    fun given_valid_response_when_min_cost_clicked_list_shown() {
      
        (fakeRepository as? FakeRestaurantRepository)?.setReturnType(Valid)

        androidComposeTestRule.apply {
            onNodeWithText(activity.getString(string.restaurant_min_cost)).performClick()
            onNodeWithTag(activity.getString(restaurant_list))
                .onChildren()
                .assertCountEquals(restaurants.size)
        }
    }

    @Test
    fun given_valid_response_when_avg_price_clicked_list_shown() {
        (fakeRepository as? FakeRestaurantRepository)?.setReturnType(Valid)

        androidComposeTestRule.apply {
            onNodeWithText(activity.getString(string.restaurant_avg_price)).performClick()
            onNodeWithTag(activity.getString(restaurant_list))
                .onChildren()
                .assertCountEquals(restaurants.size)
        }
    }

    @Test
    fun given_valid_response_when_delivery_cost_clicked_list_shown() {
      
        (fakeRepository as? FakeRestaurantRepository)?.setReturnType(Valid)

        androidComposeTestRule.apply {
            onNodeWithText(activity.getString(string.restaurant_delivery_cost)).performClick()
            onNodeWithTag(activity.getString(restaurant_list))
                .onChildren()
                .assertCountEquals(restaurants.size)
        }
    }

    @Test
    fun given_valid_response_when_newest_clicked_list_shown() {
        (fakeRepository as? FakeRestaurantRepository)?.setReturnType(Valid)

        androidComposeTestRule.apply {
            onNodeWithText(activity.getString(string.restaurant_newest)).performClick()
            onNodeWithTag(activity.getString(restaurant_list))
                .onChildren()
                .assertCountEquals(restaurants.size)
        }
    }

    @Test
    fun given_valid_response_when_popularity_clicked_list_shown() {
        (fakeRepository as? FakeRestaurantRepository)?.setReturnType(Valid)

        androidComposeTestRule.apply {
            onNodeWithText(activity.getString(string.restaurant_popularity)).performClick()
            onNodeWithTag(activity.getString(restaurant_list))
                .onChildren()
                .assertCountEquals(restaurants.size)
        }
    }

    @Test
    fun given_network_error_response_when_activity_opened_snackBar_shown() {
        (fakeRepository as? FakeRestaurantRepository)?.setReturnType(NetworkException)

        androidComposeTestRule.apply {
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
            onNodeWithTag(activity.getString(restaurant_list))
                .onChildren()
                .assertCountEquals(0)
            val text = activity.getString(R.string.unknown_error)
            onNodeWithText(text)
        }
    }
}