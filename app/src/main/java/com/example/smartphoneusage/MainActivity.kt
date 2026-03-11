package com.example.smartphoneusage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smartphoneusage.data.SettingsRepository
import com.example.smartphoneusage.ui.theme.SmartphoneUsageTheme
import com.example.smartphoneusage.ui.view.DataListScreen
import com.example.smartphoneusage.ui.view.HomeScreen
import com.example.smartphoneusage.ui.view.LoginScreen
import com.example.smartphoneusage.ui.view.RegisterScreen
import com.example.smartphoneusage.ui.view.Routes
import com.example.smartphoneusage.viewmodel.SmartphoneViewModel
import com.example.smartphoneusage.viewmodel.UserViewModel
import com.example.smartphoneusage.viewmodel.UserViewModelFactory
import com.example.smartphoneusage.ui.view.InsertScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SmartphoneUsageTheme {
                val navController = rememberNavController()
                val context = LocalContext.current

                val userViewModel: UserViewModel = viewModel(
                    factory = UserViewModelFactory(
                        SettingsRepository("AppSettings", context)
                    )
                )

                val smartphoneViewModel: SmartphoneViewModel = viewModel()

                NavHost(
                    navController = navController,
                    startDestination = Routes.Login.route
                ) {
                    composable(Routes.Login.route) {
                        LoginScreen(navController, userViewModel)
                    }

                    composable(Routes.Register.route) {
                        RegisterScreen(navController, userViewModel)
                    }

                    composable(Routes.Home.route) {
                        HomeScreen(navController, userViewModel)
                    }

                    composable(Routes.DataList.route) {
                        DataListScreen(
                            viewModel = smartphoneViewModel,
                            apiKey = BuildConfig.API_KEY
                        )
                    }

                    composable(Routes.Insert.route) {
                        InsertScreen(
                            navController = navController,
                            viewModel = smartphoneViewModel
                        )
                    }
                }
            }
        }
    }
}