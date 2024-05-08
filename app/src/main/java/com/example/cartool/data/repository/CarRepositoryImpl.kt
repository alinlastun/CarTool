package com.example.cartool.data.repository

import com.example.cartool.data.local.db.CarDao
import com.example.cartool.data.local.model.Car
import com.example.cartool.domain.repository.CarRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CarRepositoryImpl @Inject constructor(private val dao: CarDao) : CarRepository {

    override suspend fun insertDbCar(car: Car) =
        withContext(Dispatchers.IO) { dao.insertCar(car) }

    override suspend fun deleteDbCar(car: Car) =
        withContext(Dispatchers.IO) { dao.deleteCar(car) }

    override suspend fun getDbCars(): Flow<List<Car>> =
        withContext(Dispatchers.IO) { dao.getCars() }

    override suspend fun getDbCar(carId: Int): Flow<Car> =
        withContext(Dispatchers.IO) { dao.getCar(carId) }
}