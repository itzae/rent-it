package com.itgonca.rentit.base

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RentItApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}