package com.example.cartool.ui.screens

import com.example.cartool.data.local.model.Car

data class CarState(
    val cars: List<Car> = emptyList(),
    val car: Car? = null,
    var carNumber: String = "",
    val rca: String = "",
    val itp: String = "",
    val rovinieta: String = "",
    val casco: String = "",
    val extinctor: String = "",
    val trusaMedicala: String = "",
    val details:Boolean = false,
)

