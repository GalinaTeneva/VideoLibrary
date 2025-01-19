package bg.tu_varna.sit.si.video_library.ui.rented_movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import bg.tu_varna.sit.si.video_library.data.entities.RentedMovie
import bg.tu_varna.sit.si.video_library.data.repositories.RentedMovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
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
                if(id!= null) {
                    rentedMovieRepository.getRentedMovieStream(id).map { listOf(it) }
                } else {
                    rentedMovieRepository.getAllRentedMoviesStream()
                }
            }
            .map {rentedMovies -> RentedMovieHomeUiState(rentedMoviesList = rentedMovies, isLoading = false)}
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

data class RentedMovieHomeUiState(
    val rentedMoviesList: List<RentedMovie> = listOf(),
    val isLoading: Boolean = false
)