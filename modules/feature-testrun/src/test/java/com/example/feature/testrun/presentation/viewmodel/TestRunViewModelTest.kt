/*
 * Copyright 2021 Wasim Reza.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.feature.testrun.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.example.testrun.domain.usecase.TestRunUseCase
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Before

class TestRunViewModelTest {
    private val useCase: TestRunUseCase = mockk(relaxed = true)
    private val savedStateHandle: SavedStateHandle = mockk(relaxed = true)
    private lateinit var viewModel: TestRunViewModel

    private lateinit var testDispatcher: TestDispatcher

    private fun createViewModel() {
        viewModel = TestRunViewModel(
            useCase,
            savedStateHandle
        )
    }


    @Before
    fun setUp() {
        testDispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(testDispatcher)
    }

    @org.junit.After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
