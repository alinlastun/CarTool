package com.example.cartool.di

import android.content.Context
import androidx.room.Room
import com.example.cartool.data.local.db.CarDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModel {

    @Singleton
    @Provides
    fun provideCarDatabase(
        @ApplicationContext context: Context
    )= Room.databaseBuilder(
        context,
        CarDatabase::class.java,
        "CarDB",
    ).build()

    @Provides
    fun provideCarDao(dataBase: CarDatabase) = dataBase.carDao()
}