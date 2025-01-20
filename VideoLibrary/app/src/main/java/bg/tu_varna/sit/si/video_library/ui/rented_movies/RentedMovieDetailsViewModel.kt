package bg.tu_varna.sit.si.video_library.ui.rented_movies

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bg.tu_varna.sit.si.video_library.data.repositories.RentedMovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RentedMovieDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val rentedMovieRepository: RentedMovieRepository
) : ViewModel() {

    private val rentedMovieId: Int = checkNotNull(savedStateHandle["rentedMovieId"])

    val rentedMovieDetailsUiState: StateFlow<RentedMovieUiState> =
        rentedMovieRepository.getRentedMovieStream(rentedMovieId)
            .filterNotNull()
            .map {
                RentedMovieUiState(rentedMovieDetails = it.toRentedMovieDetails())
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = RentedMovieUiState()
            )

    fun deleteRecord() {
        viewModelScope.launch {
            rentedMovieRepository.deleteRentedMovie(rentedMovieDetailsUiState.value.rentedMovieDetails.toRentMovie())
        }
    }
}