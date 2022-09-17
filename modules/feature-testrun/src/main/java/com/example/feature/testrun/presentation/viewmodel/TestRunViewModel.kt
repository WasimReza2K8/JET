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
import com.example.core.viewmodel.BaseViewModel
import com.example.testrun.domain.usecase.TestRunUseCase
import com.example.feature.testrun.presentation.viewmodel.TestRunContract.Event.OnBackButtonClicked
import com.example.feature.testrun.presentation.viewmodel.TestRunContract.Event.OnViewModelInit
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TestRunViewModel @Inject constructor(
    private val useCase: TestRunUseCase,
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel<TestRunContract.Event, TestRunContract.State, TestRunContract.Effect>() {

    override fun provideInitialState() = TestRunContract.State()

    override fun handleEvent(event: TestRunContract.Event) {
        when(event){
            OnViewModelInit ->{}
            OnBackButtonClicked ->{}
        }
    }

    init{}
}
