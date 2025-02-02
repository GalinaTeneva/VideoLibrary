package bg.tu_varna.sit.si.video_library.ui.rented_movies.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bg.tu_varna.sit.si.video_library.R
import bg.tu_varna.sit.si.video_library.data.repositories.RentedMovieRepository
import bg.tu_varna.sit.si.video_library.ui.rented_movies.state.RentedMovieHomeUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class RentedMoviesHomeViewModel(rentedMovieRepository: RentedMovieRepository) : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val rentedMovieHomeUiState: StateFlow<RentedMovieHomeUiState> =
        searchQuery
            .flatMapLatest { query ->
                val id = query.toIntOrNull()
                when {
                    id == null -> {
                        rentedMovieRepository.getAllRentedMoviesStream()
                            .map { movies ->
                                RentedMovieHomeUiState(
                                    rentedMoviesList = movies,
                                    isLoading = false,
                                    messageId = null
                                )
                            }
                    }
                    rentedMovieRepository.isRentedMovieExists(id) -> {
                        rentedMovieRepository.getRentedMovieStream(id)
                            .map {rentedMovie ->
                                RentedMovieHomeUiState(
                                    rentedMoviesList = rentedMovie?.let { listOf(it) } ?: emptyList(),
                                    isLoading = false,
                                    messageId = null
                                )
                            }
                    }
                    else -> {
                        flowOf(
                            RentedMovieHomeUiState(
                                rentedMoviesList = emptyList(),
                                isLoading = false,
                                messageId = R.string.no_record_found
                            )
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
