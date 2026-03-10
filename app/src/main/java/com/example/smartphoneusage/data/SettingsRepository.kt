package com.example.smartphoneusage.data

import android.content.Context
import android.content.SharedPreferences

class SettingsRepository {
    private val context: Context
    private val sharedPreferences: SharedPreferences

    constructor(nomFitxer: String, context: Context) {
        this.context = context
        this.sharedPreferences = context.getSharedPreferences(nomFitxer, Context.MODE_PRIVATE)
    }

    fun <T> saveSettingValue(key: String, value: T) {
        with(sharedPreferences.edit()) {
            when (value) {
                is String -> putString(key, value)
                is Int -> putInt(key, value)
                is Boolean -> putBoolean(key, value)
                is Float -> putFloat(key, value)
                is Long -> putLong(key, value)
                else -> throw IllegalArgumentException("Unsupported type")
            }
            apply()
        }
    }

    fun <T> getSettingValue(key: String, defaultValue: T): T {
        return when (defaultValue) {
            is String -> sharedPreferences.getString(key, defaultValue) as T
            is Int -> sharedPreferences.getInt(key, defaultValue) as T
            is Boolean -> sharedPreferences.getBoolean(key, defaultValue) as T
            is Float -> sharedPreferences.getFloat(key, defaultValue) as T
            is Long -> sharedPreferences.getLong(key, defaultValue) as T
            else -> throw IllegalArgumentException("Unsupported type")
        }
    }

    fun guardarUsuario(valorUsuario: String) {
        val key = "user"
        sharedPreferences.edit().putString(key, valorUsuario).apply()
    }

    fun guardarPassword(valorPassword: String) {
        val key = "pwd"
        sharedPreferences.edit().putString(key, valorPassword).apply()
    }

    fun obtenirUsuario(): String {
        val key = "user"
        var value = sharedPreferences.getString(key, "") ?: ""

        if (value.isBlank()) value = "Anónimo"

        return value
    }

    fun obtenirPassword(): String {
        val key = "pwd"
        return sharedPreferences.getString(key, "") ?: ""
    }
}