package bg.tu_varna.sit.si.video_library.ui.rented_movies.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bg.tu_varna.sit.si.video_library.R
import bg.tu_varna.sit.si.video_library.data.repositories.RentedMovieRepository
import bg.tu_varna.sit.si.video_library.ui.rented_movies.state.RentedMovieHomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class RentedMoviesHomeViewModel(rentedMovieRepository: RentedMovieRepository) : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    val rentedMovieHomeUiState: StateFlow<RentedMovieHomeUiState> =
        searchQuery
            .map{ query ->
                val id = query.toIntOrNull()
                when {
                    id == null -> {
                        RentedMovieHomeUiState(
                            rentedMoviesList = rentedMovieRepository.getAllRentedMoviesStream()
                                .firstOrNull() ?: emptyList(),
                            isLoading = false,
                            messageId = null
                        )
                    }
                    rentedMovieRepository.isRentedMovieExists(id) -> {
                        val rentedMovie = rentedMovieRepository.getRentedMovieStream(id).firstOrNull()
                        RentedMovieHomeUiState(
                            rentedMoviesList = rentedMovie?.let { listOf(it) } ?: emptyList(),
                            isLoading = false,
                            messageId = null
                        )
                    }
                    else -> {
                        RentedMovieHomeUiState(
                            rentedMoviesList = emptyList(),
                            isLoading = false,
                            messageId = R.string.no_record_found
                        )
                    }
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = RentedMovieHomeUiState(isLoading = true)
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }
}
