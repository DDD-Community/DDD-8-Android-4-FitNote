package com.dogandpigs.fitnote.core

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FNApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}