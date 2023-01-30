package com.example.wasim.search

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.core.R
import com.example.core.ui.R.string.no_photo
import com.example.core.ui.R.string.search_clear
import com.example.core.ui.R.string.search_title
import com.example.wasim.MainActivity
import com.example.wasim.search.FakeSearchRepository.ReturnType.NetworkException
import com.example.wasim.search.FakeSearchRepository.ReturnType.UnknownException
import com.example.wasim.search.FakeSearchRepository.ReturnType.Valid
import com.example.wasim.search.FakeSearchRepository.ReturnType.ValidEmptyList
import com.jet.feature.detail.R.string.detail_title
import com.jet.feature.search.R.string
import com.jet.feature.search.R.string.search_list
import com.jet.feature.search.di.SearchDomainModule
import com.jet.search.domain.repository.SearchRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@MediumTest
@UninstallModules(SearchDomainModule::class)
@RunWith(AndroidJUnit4::class)
class SearchScreenTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val androidComposeTestRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var fakeRepository: SearchRepository

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun test_search_having_valid_result() {
        (fakeRepository as? FakeSearchRepository)?.setReturnType(Valid)

        androidComposeTestRule.apply {
            onNodeWithTag(activity.getString(search_list))
                .onChildren()
                .assertCountEquals(1)
        }
    }

    @Test
    fun test_search_query_clear() {
        (fakeRepository as? FakeSearchRepository)?.setReturnType(Valid)

        androidComposeTestRule.apply {
            onNodeWithTag(activity.getString(search_clear)).performClick()
            onNodeWithTag(activity.getString(search_list)).assertIsNotDisplayed()
        }
    }

    @Test
    fun test_searching_with_no_result() {
        (fakeRepository as? FakeSearchRepository)?.setReturnType(ValidEmptyList)

        androidComposeTestRule.apply {
            onNodeWithTag(activity.getString(search_clear)).performClick()
            onNodeWithTag(activity.getString(search_title)).performTextInput("qu")
            val result = activity.getString(no_photo)
            onNodeWithText(result).assertIsDisplayed()
        }
    }

    @Test
    fun test_network_error_while_searching() {
        (fakeRepository as? FakeSearchRepository)?.setReturnType(NetworkException)

        androidComposeTestRule.apply {
            val text = activity.getString(R.string.network_error)
            onNodeWithText(text)
        }
    }

    @Test
    fun test_unknown_error_while_searching() {
        (fakeRepository as? FakeSearchRepository)?.setReturnType(UnknownException)

        androidComposeTestRule.apply {
            val text = activity.getString(R.string.unknown_error)
            onNodeWithText(text)
        }
    }

    @Test
    fun test_selection_confirmation_dialog_shown() {
        (fakeRepository as? FakeSearchRepository)?.setReturnType(Valid)

        androidComposeTestRule.apply {
            onNodeWithTag(activity.getString(string.search_item)).performClick()
            onNodeWithText(activity.getString(string.search_confirmation_detail)).assertIsDisplayed()
        }
    }

    @Test
    fun test_selection_confirmation_dialog_cancelled() {
        (fakeRepository as? FakeSearchRepository)?.setReturnType(Valid)

        androidComposeTestRule.apply {
            onNodeWithTag(activity.getString(string.search_item)).performClick()
            onNodeWithText(activity.getString(string.search_confirmation_no)).performClick()
            onNodeWithText(activity.getString(string.search_confirmation_detail)).assertDoesNotExist()
        }
    }

    @Test
    fun test_selection_confirmation_dialog_confirmed() {
        (fakeRepository as? FakeSearchRepository)?.setReturnType(Valid)

        androidComposeTestRule.apply {
            onNodeWithTag(activity.getString(string.search_item)).performClick()
            onNodeWithText(activity.getString(string.search_confirmation_yes)).performClick()
            onNodeWithText(activity.getString(string.search_confirmation_detail)).assertDoesNotExist()
            onNodeWithTag(activity.getString(detail_title)).assertIsDisplayed()
        }
    }
}
