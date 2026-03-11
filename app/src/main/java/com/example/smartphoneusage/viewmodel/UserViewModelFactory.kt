package com.example.smartphoneusage.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.smartphoneusage.data.SettingsRepository

class UserViewModelFactory : ViewModelProvider.Factory {
    private val repository: SettingsRepository

    constructor(repository: SettingsRepository) {
        this.repository = repository
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(repository) as T
        }

        throw IllegalArgumentException("Clase ViewModel desconocida")
    }
}