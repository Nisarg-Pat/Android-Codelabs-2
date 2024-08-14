package com.example.a023amphibians.data.repository

import com.example.a023amphibians.data.network.AmphibianAPIService
import com.example.a023amphibians.model.Amphibian

interface AmphibianRepository {
    suspend fun getAmphibians(): List<Amphibian>
}

class NetworkAmphibianRepository(
    private val amphibianService: AmphibianAPIService
): AmphibianRepository {
    override suspend fun getAmphibians(): List<Amphibian> {
        return amphibianService.getAmphibians()
    }
}