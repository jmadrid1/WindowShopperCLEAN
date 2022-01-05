package com.example.windowshopperclean

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.internal.aggregatedroot.AggregatedRoot

@HiltAndroidApp
open class WindowShopperApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }

}