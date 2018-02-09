@file:Suppress("DEPRECATION")

package com.example.fluke.training

import android.app.Application
import com.example.fluke.training.di.AndroidModule
import com.example.fluke.training.di.ApplicationComponent
import com.example.fluke.training.di.DaggerApplicationComponent
import com.example.fluke.training.di.network.ApiManager
import com.example.fluke.training.di.network.ApiModule

class App : Application() {
    companion object {
        lateinit var appComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.builder()
                .apiManager(ApiManager())
                .apiModule(ApiModule())
                .androidModule(AndroidModule(this))
                .build()
    }
}