package com.merabk.moviesapplicationtm.di

import com.merabk.moviesapplicationtm.data.service.MoviesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
class MoviesProvidesModule {

    @Provides
    fun provideCommissionsService(
        retrofit: Retrofit
    ): MoviesApi = retrofit.create()
}