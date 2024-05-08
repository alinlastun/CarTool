package com.example.cartool.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cartool.data.local.model.Car

@Database(entities = [Car::class], version = 1, exportSchema = false)
abstract class CarDatabase: RoomDatabase() {
    abstract fun carDao() :CarDao
}