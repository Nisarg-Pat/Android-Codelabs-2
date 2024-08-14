package com.example.a023amphibians.data.network

import com.example.a023amphibians.model.Amphibian
import retrofit2.http.GET

/**
 * Retrofit service object for creating api calls
 */
interface AmphibianAPIService {
    @GET("amphibians")
    suspend fun getAmphibians(): List<Amphibian>
}