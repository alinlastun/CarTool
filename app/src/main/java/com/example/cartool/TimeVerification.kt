package com.example.cartool

import android.content.Context
import com.example.cartool.data.local.model.Car
import com.example.cartool.notification.CarNotificationService
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class TimeVerification(
    private val context: Context,
    private val carList: List<Car>
) {

    fun verify() {
        carList.forEach { car ->
            checkDays(
                context = context,
                serviceDate = car.itp ?: "",
                carNumber = car.carNumber,
                service = "ITP"
            )
            checkDays(
                context = context,
                serviceDate = car.casco ?: "",
                carNumber = car.carNumber,
                service = "Casco"
            )
            checkDays(
                context = context,
                serviceDate = car.rca ?: "",
                carNumber = car.carNumber,
                service = "RCA"
            )
            checkDays(
                context = context,
                serviceDate = car.rovinieta ?: "",
                carNumber = car.carNumber,
                service = "Rovinieta"
            )
            checkDays(
                context = context,
                serviceDate = car.extinctor ?: "",
                carNumber = car.carNumber,
                service = "Extinctor"
            )
            checkDays(
                context = context,
                serviceDate = car.trusaMedicala ?: "",
                carNumber = car.carNumber,
                service = "Trusa medicala"
            )
        }

    }

    private fun checkDays(
        context: Context,
        serviceDate: String,
        carNumber: String,
        service: String
    ) {
        val carNotification = CarNotificationService(context, carNumber, service)
        val formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy").withLocale(Locale.ENGLISH)
        val date = LocalDate.parse(serviceDate, formatter)
        val current: LocalDate = LocalDateTime.now().toLocalDate()
        if (current.year == date.year) {
            if (current.month == date.month) {
                if (current.dayOfMonth.plus(7) == date.dayOfMonth) {
                    carNotification.showBasicNotification()
                }
            }
        }
    }

}