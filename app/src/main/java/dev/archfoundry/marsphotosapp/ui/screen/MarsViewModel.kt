package dev.archfoundry.marsphotosapp.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.archfoundry.marsphotosapp.network.MarsApi
import kotlinx.coroutines.launch

class MarsViewModel : ViewModel() {
    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set

    init {
        getMarsPhotos()
    }

    fun getMarsPhotos() {
        viewModelScope.launch {
            marsUiState = MarsUiState.Loading
            marsUiState = try {
                MarsUiState.Success(MarsApi.retrofitService.getPhotos())
            } catch (_: Exception) {
                MarsUiState.Error
            }
        }
    }
}
