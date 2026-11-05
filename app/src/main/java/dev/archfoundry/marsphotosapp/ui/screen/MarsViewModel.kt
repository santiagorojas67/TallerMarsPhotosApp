package dev.archfoundry.marsphotosapp.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.archfoundry.marsphotosapp.network.MarsApi
import kotlinx.coroutines.launch


class MarsViewModel : ViewModel()  {
    var marsUiState: String by mutableStateOf("Bienvenidos a esta App")
        private set

    init {
        getMarsPhotos()
    }
    fun getMarsPhotos() {
        viewModelScope.launch {
            val result = MarsApi.retrofitService.getPhotos()
            marsUiState = result
        }
    }
}