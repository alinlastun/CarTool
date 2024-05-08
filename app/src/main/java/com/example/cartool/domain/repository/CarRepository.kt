package com.example.cartool.domain.repository

import com.example.cartool.data.local.model.Car
import kotlinx.coroutines.flow.Flow

interface CarRepository {

    suspend fun insertDbCar(car: Car)
    suspend fun deleteDbCar(car: Car)
    suspend fun getDbCars(): Flow<List<Car>>
    suspend fun getDbCar(carId: Int): Flow<Car>
}