package com.example.dessertclicker

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertClickerViewModel: ViewModel() {
    private val dessertList = Datasource.dessertList

    private val _uiState = MutableStateFlow(DessertClickerUIState(dessertList[0]))
    val uiState: StateFlow<DessertClickerUIState> = _uiState.asStateFlow()

    fun onDessertClicked() {
        val currentRevenue = uiState.value.totalRevenue + uiState.value.currentDessert.price
        val currentDessertsSold = uiState.value.numSold+1
        val dessertToShow = determineDessertToShow(currentDessertsSold)

        _uiState.update { currentState ->
            currentState.copy(
                currentDessert = dessertToShow,
                numSold = currentDessertsSold,
                totalRevenue = currentRevenue
            )
        }
    }


    /**
     * Determine which dessert to show.
     */
    private fun determineDessertToShow(
        dessertsSold: Int
    ): Dessert {
        var dessertToShow = dessertList.first()
        for (dessert in dessertList) {
            if (dessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }

        return dessertToShow
    }

}