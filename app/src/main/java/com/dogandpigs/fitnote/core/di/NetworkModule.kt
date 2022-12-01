package com.dogandpigs.fitnote.core.di

import com.dogandpigs.fitnote.data.source.remote.api.AccountApi
import com.dogandpigs.fitnote.data.source.remote.httpbuilder.RetrofitBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideJoinApi(): AccountApi = RetrofitBuilder().getRetrofit().create(AccountApi::class.java)
}