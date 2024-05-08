package com.example.cartool.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cartool.TimeVerification
import com.example.cartool.data.local.model.Car
import com.example.cartool.domain.repository.CarRepository
import com.example.cartool.ui.screens.CarEvent
import com.example.cartool.ui.screens.CarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarViewModel @Inject constructor(val repository: CarRepository) : ViewModel() {

    private val _state = MutableStateFlow(CarState())
    val state = _state.asStateFlow()

    init {
        getCars()
    }

    private fun getCars() {
        viewModelScope.launch {
            repository.getDbCars().collectLatest {
                _state.update { oldState -> oldState.copy(cars = it) }
            }
        }
    }

    fun onEvent(event: CarEvent) {
        when (event) {
            is CarEvent.DeleteCar -> {
                viewModelScope.launch {
                    repository.deleteDbCar(event.car)
                }
            }

            is CarEvent.SaveCar -> {
                val car = Car(
                    carNumber = state.value.carNumber,
                    casco = state.value.casco,
                    itp = state.value.itp,
                    extinctor = state.value.extinctor,
                    trusaMedicala = state.value.trusaMedicala,
                    rca = state.value.rca,
                    rovinieta = state.value.rovinieta
                )
                viewModelScope.launch {
                    repository.insertDbCar(car)
                }
            }

            is CarEvent.UpdateCar -> {
                val car = Car(
                    id = state.value.car?.id,
                    carNumber = state.value.car?.carNumber ?: "",
                    casco = state.value.casco,
                    itp = state.value.itp,
                    extinctor = state.value.extinctor,
                    trusaMedicala = state.value.trusaMedicala,
                    rca = state.value.rca,
                    rovinieta = state.value.rovinieta
                )
                viewModelScope.launch {
                    repository.insertDbCar(car)
                }
            }


            is CarEvent.SetCarNumber -> {
                _state.update {
                    it.copy(
                        carNumber = event.number
                    )
                }
            }

            is CarEvent.SetCasco -> {
                _state.update {
                    it.copy(
                        casco = event.casco
                    )
                }
            }

            is CarEvent.SetExtinctor -> {
                _state.update {
                    it.copy(
                        extinctor = event.extinctor
                    )
                }
            }

            is CarEvent.SetITP -> {
                _state.update {
                    it.copy(
                        itp = event.itp
                    )
                }
            }

            is CarEvent.SetRovinieta -> {
                _state.update {
                    it.copy(
                        rovinieta = event.rovinieta
                    )
                }
            }

            is CarEvent.SetTrusaMedicala -> {
                _state.update {
                    it.copy(
                        trusaMedicala = event.trusaMedicala
                    )
                }
            }

            is CarEvent.SetRCA -> {
                _state.update {
                    it.copy(
                        rca = event.rca
                    )
                }
            }

            CarEvent.AddCar -> {
                _state.update {
                    it.copy(
                        carNumber = "",
                        casco = "",
                        itp = "",
                        extinctor = "",
                        trusaMedicala = "",
                        rca = "",
                        rovinieta = ""
                    )
                }
            }

            is CarEvent.GetCar -> {
                viewModelScope.launch {
                    repository.getDbCar(event.carId).collectLatest {
                        _state.update { oldState -> oldState.copy(car = it) }
                    }
                }
            }
        }
    }
}