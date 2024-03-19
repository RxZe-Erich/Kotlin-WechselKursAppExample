package com.example.test.di

import com.example.test.GeckoConstant
import com.example.test.data.data_source.IRequestToGecko
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoinModule {
    @Provides
    @Singleton
    fun provideCoinGeckoApi():IRequestToGecko {
        return Retrofit.Builder()
            .baseUrl(GeckoConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IRequestToGecko::class.java)
    }
}