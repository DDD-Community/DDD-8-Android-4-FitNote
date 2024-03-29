package com.dogandpigs.fitnote.core.di

import com.dogandpigs.fitnote.data.source.remote.api.AccountApi
import com.dogandpigs.fitnote.data.source.remote.api.LessonApi
import com.dogandpigs.fitnote.data.source.remote.api.MemberApi
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
    fun provideJoinApi(): AccountApi =
        RetrofitBuilder().getRetrofit().create(AccountApi::class.java)
    
    @Provides
    @Singleton
    fun provideMemberApi(): MemberApi =
        RetrofitBuilder().getRetrofit().create(MemberApi::class.java)
    
    @Provides
    @Singleton
    fun provideLessonApi(): LessonApi =
        RetrofitBuilder().getRetrofit().create(LessonApi::class.java)
}