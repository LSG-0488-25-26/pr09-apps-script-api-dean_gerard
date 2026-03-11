package com.example.smartphoneusage.ui.view

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.smartphoneusage.BuildConfig
import com.example.smartphoneusage.viewmodel.SmartphoneViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertScreen(
    navController: NavController,
    viewModel: SmartphoneViewModel
) {
    val context = LocalContext.current

    var age by rememberSaveable { mutableStateOf("") }
    var gender by rememberSaveable { mutableStateOf("") }
    var occupation by rememberSaveable { mutableStateOf("") }
    var dailyPhoneHours by rememberSaveable { mutableStateOf("") }
    var sleepHours by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Insertar registro") }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top
        ) {
            OutlinedTextField(
                value = age,
                onValueChange = { age = it },
                label = { Text("Edad") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = gender,
                onValueChange = { gender = it },
                label = { Text("Género") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = occupation,
                onValueChange = { occupation = it },
                label = { Text("Ocupación") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = dailyPhoneHours,
                onValueChange = { dailyPhoneHours = it },
                label = { Text("Horas de móvil al día") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = sleepHours,
                onValueChange = { sleepHours = it },
                label = { Text("Horas de sueño") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val ageInt = age.toIntOrNull()
                    val phoneHoursDouble = dailyPhoneHours.toDoubleOrNull()
                    val sleepHoursDouble = sleepHours.toDoubleOrNull()

                    if (
                        ageInt == null ||
                        gender.isBlank() ||
                        occupation.isBlank() ||
                        phoneHoursDouble == null ||
                        sleepHoursDouble == null
                    ) {
                        Toast.makeText(
                            context,
                            "Rellena todos los campos correctamente",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        viewModel.inserirRegistre(
                            apiKey = BuildConfig.API_KEY,
                            age = ageInt,
                            gender = gender,
                            occupation = occupation,
                            dailyPhoneHours = phoneHoursDouble,
                            sleepHours = sleepHoursDouble
                        )

                        Toast.makeText(
                            context,
                            "Registro enviado",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text("Insertar")
            }
        }
    }
}