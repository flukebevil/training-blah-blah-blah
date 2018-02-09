package com.example.fluke.training.di

import android.app.Application
import android.content.Context
import com.example.fluke.training.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AndroidModule(private var mApplication: App) {

    @Provides
    @Singleton
    fun provideApplication(): Application = mApplication

    @Provides
    @Singleton
    fun provideContext(): Context = mApplication.applicationContext
}