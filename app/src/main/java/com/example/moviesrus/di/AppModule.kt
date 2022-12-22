package com.example.moviesrus.di

import com.example.moviesrus.data.remote.MovieApi
import com.example.moviesrus.data.remote.repository.MovieRepositoryImpl
import com.example.moviesrus.domain.repository.MovieRepository
import com.example.moviesrus.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
private object AppModule {

    @Provides
    @Singleton
    fun provideMovieApi(): MovieApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)

    @Provides
    @Singleton
    fun provideMovieRepository(api: MovieApi): MovieRepository = MovieRepositoryImpl(api)

}