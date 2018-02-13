package com.example.fluke.training.di.network

import com.example.fluke.training.base.BaseService
import com.example.fluke.training.model.GetData
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    fun provideUserApi(retrofit: Retrofit): BaseService = retrofit.create(BaseService::class.java)

    @Provides
    @Singleton
    fun provideGetData(userApi: BaseService): GetData = GetData(userApi)
}