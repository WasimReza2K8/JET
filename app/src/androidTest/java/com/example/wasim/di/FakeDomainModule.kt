package com.example.wasim.di

import com.example.wasim.search.FakeSearchRepository
import com.jet.search.domain.repository.SearchRepository
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
    fun bindSearchRepository(repository: FakeSearchRepository): SearchRepository
}
