package bg.tu_varna.sit.si.video_library.ui.rented_movies

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bg.tu_varna.sit.si.video_library.data.repositories.RentedMovieRepository
import bg.tu_varna.sit.si.video_library.ui.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RentedMovieEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val rentedMovieRepository: RentedMovieRepository
) : ViewModel() {
    private val _rentedMovieEditUiState = MutableStateFlow(RentedMovieUiState())
    val rentedMovieEditUiState: StateFlow<RentedMovieUiState> = _rentedMovieEditUiState.asStateFlow()

    private val rentedMovieId: Int = checkNotNull(savedStateHandle["rentedMovieId"])

    init {
        viewModelScope.launch {
            rentedMovieRepository.getRentedMovieStream(rentedMovieId).collect { rentedMovie ->
                _rentedMovieEditUiState.value = RentedMovieUiState(
                    rentedMovieDetails = rentedMovie.toRentedMovieDetails()
                )
            }
        }
    }

    fun updateUiState(rentedMovieDetails: RentedMovieDetails) {
        _rentedMovieEditUiState.value = _rentedMovieEditUiState.value.copy(rentedMovieDetails = rentedMovieDetails)
    }

    suspend fun updateRentedMovie() {
        val rentedMovie = _rentedMovieEditUiState.value.rentedMovieDetails.toRentMovie()
        rentedMovieRepository.updateRentedMove(rentedMovie)
    }
}