package bg.tu_varna.sit.si.video_library.ui.rented_movies

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import bg.tu_varna.sit.si.video_library.data.repositories.RentedMovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RentedMovieInsertViewModel(private val rentedMovieRepository: RentedMovieRepository) : ViewModel() {
    private val _rentedMovieInsertUiState = MutableStateFlow(RentedMovieUiState())
    val rentedMovieInsertUiState: StateFlow<RentedMovieUiState> = _rentedMovieInsertUiState.asStateFlow()

    fun updateUiState(rentedMovieDetails: RentedMovieDetails) {
        _rentedMovieInsertUiState.value = _rentedMovieInsertUiState.value.copy(rentedMovieDetails = rentedMovieDetails)
    }

    suspend fun saveRentedMovie() {
        val rentedMovie = _rentedMovieInsertUiState.value.rentedMovieDetails.toRentMovie()
        rentedMovieRepository.insertRentedMovie(rentedMovie)
    }
}

