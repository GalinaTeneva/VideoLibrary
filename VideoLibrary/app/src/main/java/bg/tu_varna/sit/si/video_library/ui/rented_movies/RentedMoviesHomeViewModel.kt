package bg.tu_varna.sit.si.video_library.ui.rented_movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bg.tu_varna.sit.si.video_library.data.entities.RentedMovie
import bg.tu_varna.sit.si.video_library.data.repositories.RentedMovieRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class RentedMoviesNomeViewModel(rentedMovieRepository: RentedMovieRepository) : ViewModel() {
    val rentedMovieHomeUiState: StateFlow<RentedMovieHomeUiState> =
        rentedMovieRepository.getAllRentedMoviesStream().map {RentedMovieHomeUiState(it)}
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = RentedMovieHomeUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class RentedMovieHomeUiState(val rentedMoviesList: List<RentedMovie> = listOf())