package com.example.a023amphibians.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.a023amphibians.MainApplication
import com.example.a023amphibians.data.repository.AmphibianRepository
import com.example.a023amphibians.model.Amphibian
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface UIState {
    data class Success(val amphibians: List<Amphibian>): UIState
    object Error: UIState
    object Loading: UIState
}

class AmphibianViewModel(private val amphibianRepository: AmphibianRepository): ViewModel() {
    var uiState: UIState by mutableStateOf(UIState.Loading)
        private set

    init {
        getAmphibians()
    }

    private fun getAmphibians() {
        viewModelScope.launch {
            uiState = try {
                val amphibians = amphibianRepository.getAmphibians()
                UIState.Success(amphibians)
            } catch (e: IOException) {
                UIState.Error
            } catch (e: HttpException) {
                UIState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MainApplication)
                val amphibianRepository = application.container.amphibianRepository
                AmphibianViewModel(amphibianRepository)
            }
        }
    }

}
