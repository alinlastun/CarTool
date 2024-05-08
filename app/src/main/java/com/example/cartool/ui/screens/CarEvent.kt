package com.example.cartool.ui.screens

import com.example.cartool.data.local.model.Car

sealed interface CarEvent {
    data object SaveCar : CarEvent
    data object AddCar : CarEvent
    data class GetCar(val carId: Int) : CarEvent
    data class UpdateCar(val carId: Int?) : CarEvent
    data class DeleteCar(val car: Car) : CarEvent

    data class SetCasco(val casco: String) : CarEvent
    data class SetCarNumber(val number: String) : CarEvent
    data class SetITP(val itp: String) : CarEvent
    data class SetRovinieta(val rovinieta: String) : CarEvent
    data class SetTrusaMedicala(val trusaMedicala: String) : CarEvent
    data class SetExtinctor(val extinctor: String) : CarEvent
    data class SetRCA(val rca: String) : CarEvent
}
