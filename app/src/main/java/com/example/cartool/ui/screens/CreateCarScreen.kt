package com.example.cartool.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cartool.data.local.model.Car
import com.example.cartool.ui.theme.CarToolTheme


@Composable
fun CreateCarScreen(
    navController: NavHostController,
    state: CarState,
    onEvent: (CarEvent) -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var textInput by remember { mutableStateOf(state.carNumber) }
        val maxChar = 10
        TextField(
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Characters
            ),
            value = textInput,
            isError = textInput.isNotEmpty(),
            onValueChange = {
                if (it.length <= maxChar) textInput = it
                onEvent(CarEvent.SetCarNumber(it))
            },
            singleLine = true,
            placeholder = {
                Text(text = "B-123-ABC")
            },
            label = { Text("Numar Masina") }


        )
        onEvent(CarEvent.SetExtinctor(customDateField(nameInput = "Extinctor")))
        onEvent(CarEvent.SetITP(customDateField(nameInput = "ITP")))
        onEvent(CarEvent.SetRCA(customDateField(nameInput = "RCA")))
        onEvent(CarEvent.SetRovinieta(customDateField(nameInput = "Rovinieta")))
        onEvent(CarEvent.SetTrusaMedicala(customDateField(nameInput = "Trusa Medicala")))
        onEvent(CarEvent.SetCasco(customDateField(nameInput = "Casco")))


        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = {
                if (textInput.isEmpty()) {
                    Toast.makeText(context, "Numar Masina is mandatory!!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    onEvent(CarEvent.SaveCar)
                    navController.navigate(Screen.HomeScreen.route) {
                        popUpTo(0)
                    }
                }
            }) {
                Text(text = "Save")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateCarScreenPreview() {
    CarToolTheme {
        CreateCarScreen(
            rememberNavController(), CarState(
                cars = emptyList(),
                car = Car(
                    id = null,
                    carNumber = "",
                ),
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                details = false
            ), {}
        )
    }
}

