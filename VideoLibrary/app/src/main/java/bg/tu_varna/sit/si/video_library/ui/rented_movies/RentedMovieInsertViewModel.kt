package bg.tu_varna.sit.si.video_library.ui.rented_movies

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import bg.tu_varna.sit.si.video_library.data.repositories.RentedMovieRepository

class RentedMovieInsertViewModel(private val rentedMovieRepository: RentedMovieRepository) : ViewModel() {
    var rentedMovieUiState by mutableStateOf(RentedMovieInsertUiState())
    private set
}

data class RentedMovieInsertUiState(
    val rentedMovieDetails: RentedMovieDetails = RentedMovieDetails())