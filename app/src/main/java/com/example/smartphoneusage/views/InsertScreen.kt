package com.example.smartphoneusage.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smartphoneusage.BuildConfig
import com.example.smartphoneusage.viewmodel.SmartphoneViewModel
import androidx.compose.foundation.border
import androidx.compose.ui.text.style.TextAlign

@Composable
fun InsertScreen(
    navController: NavController,
    viewModel: SmartphoneViewModel
) {
    var age by rememberSaveable { mutableStateOf("") }
    var gender by rememberSaveable { mutableStateOf("") }
    var occupation by rememberSaveable { mutableStateOf("") }
    var dailyPhoneHours by rememberSaveable { mutableStateOf("") }
    var sleepHours by rememberSaveable { mutableStateOf("") }

    var genderExpanded by rememberSaveable { mutableStateOf(false) }
    var occupationExpanded by rememberSaveable { mutableStateOf(false) }

    var localMessage by rememberSaveable { mutableStateOf("") }

    val error by viewModel.error.collectAsState()
    val missatgeResposta by viewModel.missatgeResposta.collectAsState()
    val loading by viewModel.loading.collectAsState()

    val genderOptions = listOf("Male", "Female", "Other")
    val occupationOptions = listOf("Professional", "Student", "Freelancer", "Business Owner")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 28.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Insert record",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Age",
                fontSize = 14.sp,
                color = Color.DarkGray,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(4.dp))

            TextField(
                value = age,
                onValueChange = {
                    age = it
                    localMessage = ""
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color(0xFFE0E0E0),
                    disabledIndicatorColor = Color(0xFFE0E0E0),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    cursorColor = Color.Black
                ),
                placeholder = { Text("") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Gender",
                fontSize = 14.sp,
                color = Color.DarkGray,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(4.dp))

            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = if (gender.isBlank()) "Select an option" else gender,
                    color = if (gender.isBlank()) Color.Gray else Color.Black,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { genderExpanded = true }
                        .padding(vertical = 16.dp)
                )

                DropdownMenu(
                    expanded = genderExpanded,
                    onDismissRequest = { genderExpanded = false },
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .background(Color.White)
                        .border(1.dp, Color.Black, RoundedCornerShape(6.dp))
                ) {
                    genderOptions.forEach { option ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = option,
                                    color = Color.Black,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            },
                            onClick = {
                                gender = option
                                genderExpanded = false
                                localMessage = ""
                            }
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color(0xFFE0E0E0))
                        .align(Alignment.BottomStart)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Occupation",
                fontSize = 14.sp,
                color = Color.DarkGray,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(4.dp))

            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = if (occupation.isBlank()) "Select an option" else occupation,
                    color = if (occupation.isBlank()) Color.Gray else Color.Black,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { occupationExpanded = true }
                        .padding(vertical = 16.dp)
                )

                DropdownMenu(
                    expanded = occupationExpanded,
                    onDismissRequest = { occupationExpanded = false },
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .background(Color.White)
                        .border(1.dp, Color.Black, RoundedCornerShape(6.dp))
                ) {
                    occupationOptions.forEach { option ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = option,
                                    color = Color.Black,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            },
                            onClick = {
                                occupation = option
                                occupationExpanded = false
                                localMessage = ""
                            }
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color(0xFFE0E0E0))
                        .align(Alignment.BottomStart)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Daily phone hours",
                fontSize = 14.sp,
                color = Color.DarkGray,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(4.dp))

            TextField(
                value = dailyPhoneHours,
                onValueChange = {
                    dailyPhoneHours = it
                    localMessage = ""
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color(0xFFE0E0E0),
                    disabledIndicatorColor = Color(0xFFE0E0E0),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    cursorColor = Color.Black
                ),
                placeholder = { Text("") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Sleep hours",
                fontSize = 14.sp,
                color = Color.DarkGray,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(4.dp))

            TextField(
                value = sleepHours,
                onValueChange = {
                    sleepHours = it
                    localMessage = ""
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color(0xFFE0E0E0),
                    disabledIndicatorColor = Color(0xFFE0E0E0),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    cursorColor = Color.Black
                ),
                placeholder = { Text("") }
            )

            Spacer(modifier = Modifier.height(20.dp))

            when {
                localMessage.isNotBlank() -> {
                    Text(
                        text = localMessage,
                        color = Color(0xFFB3261E),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }

                !error.isNullOrBlank() -> {
                    Text(
                        text = error ?: "",
                        color = Color(0xFFB3261E),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }

                missatgeResposta != null && missatgeResposta?.status == "ok" -> {
                    navController.popBackStack()
                }
            }

            Button(
                onClick = {
                    val ageInt = age.toIntOrNull()
                    val phoneHoursDouble = dailyPhoneHours.toDoubleOrNull()
                    val sleepHoursDouble = sleepHours.toDoubleOrNull()

                    when {
                        age.isBlank() ||
                                gender.isBlank() ||
                                occupation.isBlank() ||
                                dailyPhoneHours.isBlank() ||
                                sleepHours.isBlank() -> {
                            localMessage = "You cannot leave any field empty."
                        }

                        ageInt == null || phoneHoursDouble == null || sleepHoursDouble == null -> {
                            localMessage = "Fill in all fields correctly."
                        }

                        else -> {
                            localMessage = ""
                            viewModel.inserirRegistre(
                                apiKey = BuildConfig.API_KEY,
                                age = ageInt,
                                gender = gender,
                                occupation = occupation,
                                dailyPhoneHours = phoneHoursDouble,
                                sleepHours = sleepHoursDouble
                            )
                        }
                    }
                },
                enabled = !loading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(18.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White,
                    disabledContainerColor = Color(0xFF555555),
                    disabledContentColor = Color.White
                )
            ) {
                Text(
                    text = if (loading) "Inserting..." else "Insert",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(36.dp))
        }
    }
}