package com.merabk.moviesapplicationtm.di

import com.merabk.moviesapplicationtm.data.mapper.AllFilmsMapper
import com.merabk.moviesapplicationtm.data.mapper.FilmDetailsMapper
import com.merabk.moviesapplicationtm.data.repo.MoviesRepoImpl
import com.merabk.moviesapplicationtm.domain.repo.MoviesRepo
import com.merabk.moviesapplicationtm.util.Dispatchers
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MoviesBindsModule {

    @Binds
    @Singleton
    abstract fun bindMyRepository(
        myRepositoryImpl: MoviesRepoImpl
    ): MoviesRepo

    @Binds
    abstract fun bindAllFilmsMapper(
        allFilmsMapper: AllFilmsMapper.AllFilmsMapperImpl
    ): AllFilmsMapper

    @Binds
    abstract fun bindFilmDetailsMapper(
        filmDetailsMapper: FilmDetailsMapper.FilmDetailsMapperImpl
    ): FilmDetailsMapper

    @Binds
    abstract fun bindDispatchers(
        dispatchersImpl: Dispatchers.DispatchersImpl
    ): Dispatchers

}