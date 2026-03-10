package com.example.smartphoneusage.api

import com.example.smartphoneusage.model.GetDataResponse
import com.example.smartphoneusage.model.GetOccupationResponse
import com.example.smartphoneusage.model.GetStatsResponse
import com.example.smartphoneusage.model.PostRequest
import com.example.smartphoneusage.model.PostResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SmartphoneApiService {

    /**
     * GET de totes les dades de la pestanya "SmartphoneData"
     */
    @GET("exec")
    suspend fun getDades(
        @Query("apiKey") apiKey: String,
        @Query("type") type: String = "data"
    ): GetDataResponse

    /**
     * GET d'estadístiques generals
     */
    @GET("exec")
    suspend fun getStats(
        @Query("apiKey") apiKey: String,
        @Query("type") type: String = "stats"
    ): GetStatsResponse

    /**
     * GET filtrant per ocupació
     * Exemple: Student, Freelancer, Professional...
     */
    @GET("exec")
    suspend fun getByOccupation(
        @Query("apiKey") apiKey: String,
        @Query("type") type: String = "occupation",
        @Query("value") value: String
    ): GetOccupationResponse

    /**
     * POST per inserir una fila nova
     */
    @POST("exec")
    suspend fun inserirFila(
        @Body body: PostRequest
    ): PostResponse
}