package com.example.cartool

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cartool.ui.screens.CarListScreen
import com.example.cartool.ui.screens.CreateCarScreen
import com.example.cartool.ui.screens.DetailsCarScreen
import com.example.cartool.ui.screens.Screen
import com.example.cartool.ui.theme.CarToolTheme
import com.example.cartool.ui.viewmodel.CarViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: CarViewModel by viewModels()
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val postNotificationPermission =
                rememberPermissionState(permission = android.Manifest.permission.POST_NOTIFICATIONS)

            LaunchedEffect(key1 = true) {
                if (!postNotificationPermission.status.isGranted) {
                    postNotificationPermission.launchPermissionRequest()
                }
            }

            CarToolTheme {
                val state by viewModel.state.collectAsState()
                if(state.cars.isNotEmpty()){
                    TimeVerification(LocalContext.current, state.cars).verify()
                }

                val nacController = rememberNavController()
                NavHost(navController = nacController, startDestination = Screen.HomeScreen.route) {
                    composable(route = Screen.HomeScreen.route) {

                        CarListScreen(
                            nacController = nacController,
                            state = state,
                            onEvent = viewModel::onEvent
                        )
                    }
                    composable(route = Screen.CreateCarScreen.route) {
                        CreateCarScreen(
                            navController = nacController,
                            state = state,
                            onEvent = viewModel::onEvent
                        )

                    }
                    composable(route = Screen.DetailsCarScreen.route) {
                        DetailsCarScreen(
                            nacController = nacController,
                            state = state,
                            onEvent = viewModel::onEvent
                        )
                    }
                }

            }
        }
    }
}
