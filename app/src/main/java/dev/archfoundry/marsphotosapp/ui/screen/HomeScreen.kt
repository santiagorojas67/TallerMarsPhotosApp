package dev.archfoundry.marsphotosapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.archfoundry.marsphotosapp.R
import dev.archfoundry.marsphotosapp.network.MarsPhoto

sealed interface MarsUiState {
    data class Success(val photos: List<MarsPhoto>) : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}

@Composable
fun HomeScreen(marsUiState: MarsUiState, modifier: Modifier = Modifier) {
    when (marsUiState) {
        is MarsUiState.Loading -> LoadingScreen(modifier.fillMaxSize())
        is MarsUiState.Success -> ResultScreen(marsUiState.photos, modifier)
        is MarsUiState.Error -> ErrorScreen(modifier.fillMaxSize())
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator()
            Text("Cargando...")
        }
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(R.drawable.ic_connection_error), contentDescription = null)
        Text("Error", modifier = Modifier.padding(6.dp))
    }
}

@Composable
fun ResultScreen(photos: List<MarsPhoto>, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Se encontraron ${photos.size} fotos de Marte.")
    }
}
