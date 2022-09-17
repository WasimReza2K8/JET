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

package com.example.feature.testrun.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton
import com.example.feature.testrun.domain.usecase.TestRunUseCaseImpl
import com.example.testrun.domain.usecase.TestRunUseCase
import com.example.testrun.domain.repository.TestRunRepository
import com.example.feature.testrun.data.repository.TestRunRepositoryImpl
import com.example.feature.testrun.presentation.launcher.TestRunLauncherImpl
import com.example.testrun.presentation.TestRunLauncher
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface TestRunDomainModule {
    @Binds
    fun bindTestRunRepository(repository: TestRunRepositoryImpl): TestRunRepository

    @Binds
    @ViewModelScoped
    fun bindTestRunUseCase(useCase: TestRunUseCaseImpl): TestRunUseCase
}

@Module
@InstallIn(ActivityComponent::class)
interface TestRunPresentationModule {
    @Binds
    fun bindLauncher(launcher: TestRunLauncherImpl): TestRunLauncher
}
