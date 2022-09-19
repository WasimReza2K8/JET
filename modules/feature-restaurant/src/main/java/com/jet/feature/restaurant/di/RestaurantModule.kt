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

package com.jet.feature.restaurant.di

import com.jet.feature.restaurant.data.repository.RestaurantRepositoryImpl
import com.jet.feature.restaurant.domain.usecase.GetDefaultRestaurantsUseCase
import com.jet.restaurant.domain.repository.RestaurantRepository
import com.jet.restaurant.domain.usecase.RestaurantUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface RestaurantDomainModule {
    @Binds
    fun bindRestaurantRepository(repository: RestaurantRepositoryImpl): RestaurantRepository

  /*  @Binds
    @ViewModelScoped
    fun bindRestaurantUseCase(useCase: GetDefaultRestaurantsUseCase): RestaurantUseCase*/
}

/*@Module
@InstallIn(ActivityComponent::class)
interface RestaurantPresentationModule {
    @Binds
    fun bindLauncher(launcher: RestaurantLauncherImpl): RestaurantLauncher
}*/
