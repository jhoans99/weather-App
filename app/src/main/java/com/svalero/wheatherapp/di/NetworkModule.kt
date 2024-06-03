package com.svalero.wheatherapp.di

import com.svalero.wheatherapp.coomons.interceptor.NetworkInterceptor
import com.svalero.wheatherapp.data.datasource.services.WeatherApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideWeatherApiService(retrofit: Retrofit): WeatherApiService =
        retrofit.create(WeatherApiService::class.java)

    @Provides
    @Singleton
    fun provideOkHttpClient(networkInterceptor: NetworkInterceptor): OkHttpClient =
        OkHttpClient.Builder().addNetworkInterceptor(networkInterceptor).build()

}