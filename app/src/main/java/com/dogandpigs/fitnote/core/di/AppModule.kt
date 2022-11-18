package com.dogandpigs.fitnote.core.di

import android.content.Context
import com.dogandpigs.fitnote.core.FNApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Singleton
    @Provides
    fun provideFNApplication(@ApplicationContext context: Context): FNApplication {
        return context as FNApplication
    }
}