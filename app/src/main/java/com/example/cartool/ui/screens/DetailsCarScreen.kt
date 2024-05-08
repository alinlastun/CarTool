package com.example.cartool.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cartool.data.local.model.Car
import com.example.cartool.ui.theme.CarToolTheme
import java.time.LocalDate

import java.time.format.DateTimeFormatter
import java.util.Locale


@Composable
fun DetailsCarScreen(
    nacController: NavHostController,
    state: CarState,
    onEvent: (CarEvent) -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = state.car?.carNumber?:"",
            fontSize = 30.sp
        )

        onEvent(CarEvent.SetExtinctor(customDateField(state.car?.extinctor,"Extinctor")))
        onEvent(CarEvent.SetITP(customDateField(state.car?.itp,"ITP")))
        onEvent(CarEvent.SetRCA(customDateField(state.car?.rca,"RCA")))
        onEvent(CarEvent.SetTrusaMedicala(customDateField(state.car?.trusaMedicala,"Trusa Medicala")))
        onEvent(CarEvent.SetCasco(customDateField(state.car?.casco,"Casco")))
        onEvent(CarEvent.SetRovinieta(customDateField(state.car?.rovinieta,"Rovinieta")))



        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = {
                onEvent(CarEvent.UpdateCar(carId = state.car?.id))
                nacController.navigate(Screen.HomeScreen.route)
            }) {
                Text(text = "Save")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsCarScreenPreview() {
    CarToolTheme {
        DetailsCarScreen(
            rememberNavController(), CarState(
                cars = emptyList(),
                car = Car(
                    id = null,
                    carNumber = "B-184-AER",
                    extinctor = "03.06.2024",
                    itp = "03.06.1988"
                ),
                "AG-64-NOV",
                "",
                "03.06.1988",
                "03.06.1988",
                "03.06.1988",
                "03.06.1988",
                "",
                details = false
            ),{}
        )
    }
}
