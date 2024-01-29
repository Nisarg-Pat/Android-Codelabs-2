package com.example.dessertclicker

import com.example.dessertclicker.model.Dessert

data class DessertClickerUIState(
    val currentDessert: Dessert,
    val numSold: Int = 0,
    val totalRevenue: Int = 0
)