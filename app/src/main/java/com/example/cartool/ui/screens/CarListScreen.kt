package com.example.cartool.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cartool.TimeVerification
import com.example.cartool.data.local.model.Car
import com.example.cartool.ui.theme.CarToolTheme


@Composable
fun CarListScreen(
    nacController: NavHostController,
    state: CarState,
    onEvent: (CarEvent) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(CarEvent.AddCar)
                nacController.navigate(Screen.CreateCarScreen.route)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add contact"
                )
            }
        },
    ) { _ ->
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.cars) { car ->
                Row(
                    modifier = Modifier
                        .border(1.dp, Color.Black)
                        .height(IntrinsicSize.Min),
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .clickable {
                                onEvent(CarEvent.GetCar(carId = car.id ?: 0))
                                nacController
                                    .navigate(Screen.DetailsCarScreen.route)
                            }
                            .fillMaxHeight()
                            .weight(1F)


                    ) {
                        Text(
                            text = car.carNumber,
                            fontSize = 20.sp
                        )
                    }
                    IconButton(onClick = {
                        onEvent(CarEvent.DeleteCar(car))
                    }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete contact"
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CarListScreenPreview() {
    CarToolTheme {
        CarListScreen(
            rememberNavController(), CarState(
                cars = listOf(
                    Car(
                        id = null,
                        carNumber = "B-184-AER",
                    )
                ),
                car = Car(
                    id = null,
                    carNumber = "B-184-AER",
                ),
                "AG-64-NOV",
                "",
                "03.06.1988",
                "03.06.1988",
                "03.06.1988",
                "03.06.1988",
                "",
                details = false
            ), {}
        )
    }
}