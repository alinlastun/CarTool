package com.example.cartool.di

import com.example.cartool.data.repository.CarRepositoryImpl
import com.example.cartool.domain.repository.CarRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsCarRepository(
        carRepositoryImpl: CarRepositoryImpl
    ): CarRepository
}