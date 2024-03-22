package com.merabk.moviesapplicationtm.di

import com.merabk.moviesapplicationtm.data.repo.MoviesRepoImpl
import com.merabk.moviesapplicationtm.domain.repo.MoviesRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMyRepository(
        myRepositoryImpl: MoviesRepoImpl
    ): MoviesRepo
}