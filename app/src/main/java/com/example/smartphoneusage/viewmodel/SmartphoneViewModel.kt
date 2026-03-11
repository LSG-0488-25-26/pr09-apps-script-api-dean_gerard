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

                val resposta = RetrofitInstance.api.getDades(apiKey)

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

            } catch (e: Exception) {
                _error.value = e.message ?: "Error al insertar registro"
            } finally {
                _loading.value = false
            }
        }
    }
}