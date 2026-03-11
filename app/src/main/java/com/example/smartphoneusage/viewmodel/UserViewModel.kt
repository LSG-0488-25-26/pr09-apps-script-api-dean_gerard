package com.example.smartphoneusage.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.smartphoneusage.data.SettingsRepository

class UserViewModel(repository: SettingsRepository) : ViewModel() {

    private val repository: SettingsRepository = repository

    var username by mutableStateOf(repository.obtenirUsuario())
        private set

    var password by mutableStateOf(repository.obtenirPassword())
        private set

    fun registerUser(user: String, pwd: String) {
        username = user
        password = pwd

        repository.guardarUsuario(user)
        repository.guardarPassword(pwd)
    }

    fun login(user: String, pwd: String): Boolean {
        val savedUser = repository.obtenirUsuario()
        val savedPassword = repository.obtenirPassword()

        username = savedUser
        password = savedPassword

        return user == savedUser && pwd == savedPassword
    }

    fun validarContrasena(password: String): Boolean {
        val regex = Regex("^(?=.*[0-9])(?=.*[^A-Za-z0-9]).{8,}$")
        return regex.matches(password)
    }
}