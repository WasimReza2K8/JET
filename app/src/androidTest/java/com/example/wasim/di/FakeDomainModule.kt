package com.example.wasim.di

import com.example.wasim.restaurant.FakeRestaurantRepository
import com.jet.restaurant.domain.repository.RestaurantRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface FakeDomainModule {
    @Binds
    @Singleton
    fun bindRestaurantRepository(repository: FakeRestaurantRepository): RestaurantRepository
}
