package com.example.cartool.ui.screens

sealed class Screen(val route: String) {
    data object HomeScreen : Screen("main_screen")
    data object CreateCarScreen : Screen("create_screen")
    data object DetailsCarScreen : Screen("details_screen")


}