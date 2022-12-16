package com.dogandpigs.fitnote.core

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FNApplication: Application() {
    override fun onCreate() {
        pref = PreferenceManager(this)
        super.onCreate()
    }
    companion object {
        lateinit var pref: PreferenceManager
    }
}