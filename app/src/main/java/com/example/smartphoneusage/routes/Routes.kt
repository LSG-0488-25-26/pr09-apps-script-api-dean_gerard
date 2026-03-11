package com.example.smartphoneusage.ui.view

sealed class Routes(val route: String) {
    data object Login : Routes("login")
    data object Register : Routes("register")
    data object Home : Routes("home")
    data object DataList : Routes("data_list")
    data object Insert : Routes("insert")
}