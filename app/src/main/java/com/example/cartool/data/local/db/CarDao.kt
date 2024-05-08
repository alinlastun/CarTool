package com.example.cartool.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.cartool.data.local.model.Car
import kotlinx.coroutines.flow.Flow

@Dao
interface CarDao {

    @Upsert
    fun insertCar(car: Car)

    @Delete
    fun deleteCar(car: Car)

    @Update
    suspend fun update(car: Car)

    @Query("SELECT * FROM car" )
    fun getCars(): Flow<List<Car>>

    @Query("SELECT * FROM car WHERE id=:id ")
    fun getCar(id: Int): Flow<Car>
}