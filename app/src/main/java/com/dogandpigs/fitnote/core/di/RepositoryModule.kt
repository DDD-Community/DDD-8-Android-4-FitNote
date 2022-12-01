package com.dogandpigs.fitnote.core.di

import com.dogandpigs.fitnote.data.repository.JoinRepository
import com.dogandpigs.fitnote.data.source.remote.api.JoinApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideJoinRepository(api: JoinApi) = JoinRepository(api)
}