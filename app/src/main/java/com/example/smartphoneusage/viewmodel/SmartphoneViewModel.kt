package com.example.smartphoneusage.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartphoneusage.api.RetrofitInstance
import com.example.smartphoneusage.model.GetStatsResponse
import com.example.smartphoneusage.model.PostRequest
import com.example.smartphoneusage.model.PostResponse
import com.example.smartphoneusage.model.SmartphoneRecord
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SmartphoneViewModel : ViewModel() {

    private val _records = MutableStateFlow<List<SmartphoneRecord>>(emptyList())
    val records: StateFlow<List<SmartphoneRecord>> = _records.asStateFlow()

    private val _stats = MutableStateFlow<GetStatsResponse?>(null)
    val stats: StateFlow<GetStatsResponse?> = _stats.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _missatgeResposta = MutableStateFlow<PostResponse?>(null)
    val missatgeResposta: StateFlow<PostResponse?> = _missatgeResposta.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    fun carregarDades(apiKey: String) {
        viewModelScope.launch {
            try {
                _loading.value = true
                _error.value = null

                val resposta = RetrofitInstance.api.getDades(apiKey,
                    limit = 100
                )

                if (resposta.status == "ok") {
                    _records.value = resposta.data
                } else {
                    _error.value = "Error al cargar datos"
                }

            } catch (e: Exception) {
                _error.value = e.message ?: "Error desconocido"
            } finally {
                _loading.value = false
            }
        }
    }

    fun carregarStats(apiKey: String) {
        viewModelScope.launch {
            try {
                _loading.value = true
                _error.value = null
                _records.value = emptyList()

                val resposta = RetrofitInstance.api.getStats(apiKey)

                if (resposta.status == "ok") {
                    _stats.value = resposta
                } else {
                    _error.value = "Error al cargar estadísticas"
                }

            } catch (e: Exception) {
                _error.value = e.message ?: "Error desconocido"
            } finally {
                _loading.value = false
            }
        }
    }

    fun netejarStats() {
        _stats.value = null
    }

    fun filtrarPerOcupacio(apiKey: String, ocupacio: String) {
        viewModelScope.launch {
            try {
                _loading.value = true
                _error.value = null

                val resposta = RetrofitInstance.api.getByOccupation(
                    apiKey = apiKey,
                    value = ocupacio
                )

                if (resposta.status == "ok") {
                    _records.value = resposta.data
                } else {
                    _error.value = "Error al filtrar por ocupación"
                }

            } catch (e: Exception) {
                _error.value = e.message ?: "Error desconocido"
            } finally {
                _loading.value = false
            }
        }
    }

    fun inserirRegistre(
        apiKey: String,
        age: Int,
        gender: String,
        occupation: String,
        dailyPhoneHours: Double,
        sleepHours: Double
    ) {
        viewModelScope.launch {
            try {
                _loading.value = true
                _error.value = null
                _missatgeResposta.value = null

                val genderOptions = listOf("Male", "Female", "Other")
                val occupationOptions = listOf("Professional", "Student", "Freelancer", "Business Owner")

                when {
                    gender.isBlank() -> {
                        _error.value = "Tienes que seleccionar una opción de género."
                        return@launch
                    }

                    occupation.isBlank() -> {
                        _error.value = "Tienes que seleccionar una opción de ocupación."
                        return@launch
                    }

                    age < 18 || age > 85 -> {
                        _error.value = "La edad debe estar entre 18 y 85 años."
                        return@launch
                    }

                    gender !in genderOptions -> {
                        _error.value = "El género seleccionado no es válido."
                        return@launch
                    }

                    occupation !in occupationOptions -> {
                        _error.value = "La ocupación seleccionada no es válida."
                        return@launch
                    }

                    dailyPhoneHours < 0 || dailyPhoneHours > 24 -> {
                        _error.value = "Las horas de móvil deben estar entre 0 y 24."
                        return@launch
                    }

                    sleepHours < 0 || sleepHours > 24 -> {
                        _error.value = "Las horas de sueño deben estar entre 0 y 24."
                        return@launch
                    }

                    dailyPhoneHours + sleepHours > 24 -> {
                        _error.value = "La suma de horas de móvil y sueño no puede ser mayor que 24."
                        return@launch
                    }
                }

                val body = PostRequest(
                    apiKey = apiKey,
                    age = age,
                    gender = gender,
                    occupation = occupation,
                    dailyPhoneHours = dailyPhoneHours,
                    sleepHours = sleepHours
                )

                val resposta = RetrofitInstance.api.inserirFila(body)
                _missatgeResposta.value = resposta

                if (resposta.status != "ok") {
                    _error.value = resposta.message
                }

            } catch (e: Exception) {
                _error.value = e.message ?: "Error al insertar registro"
            } finally {
                _loading.value = false
            }
        }
    }
}