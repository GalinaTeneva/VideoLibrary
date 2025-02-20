package bg.tu_varna.sit.si.video_library.ui.rented_movies.insert

import androidx.lifecycle.ViewModel
import bg.tu_varna.sit.si.video_library.R
import bg.tu_varna.sit.si.video_library.data.repositories.CustomerRepository
import bg.tu_varna.sit.si.video_library.data.repositories.MovieRepository
import bg.tu_varna.sit.si.video_library.data.repositories.RentedMovieRepository
import bg.tu_varna.sit.si.video_library.ui.rented_movies.state.RentedMovieFormData
import bg.tu_varna.sit.si.video_library.ui.rented_movies.state.RentedMovieUiState
import bg.tu_varna.sit.si.video_library.ui.rented_movies.state.toRentMovie
import bg.tu_varna.sit.si.video_library.ui.rented_movies.utils.validateRentedMovie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RentedMovieInsertViewModel(
    private val rentedMovieRepository: RentedMovieRepository,
    private val movieRepository: MovieRepository,
    private val customerRepository: CustomerRepository
) : ViewModel() {

    private val _rentedMovieInsertUiState = MutableStateFlow(RentedMovieUiState())
    val rentedMovieInsertUiState: StateFlow<RentedMovieUiState> = _rentedMovieInsertUiState.asStateFlow()

    fun updateUiState(rentedMovieFormData: RentedMovieFormData) {
        _rentedMovieInsertUiState.value = _rentedMovieInsertUiState.value.copy(rentedMovieFormData = rentedMovieFormData)
    }

    suspend fun saveRentedMovie(): Int {
        val validationErrorId = validateRentedMovie(
            _rentedMovieInsertUiState.value.rentedMovieFormData,
            movieRepository,
            customerRepository,
            rentedMovieRepository
        )

        if(validationErrorId != null) {
            return validationErrorId
        }

        val rentedMovie = _rentedMovieInsertUiState.value.rentedMovieFormData.toRentMovie()
        rentedMovieRepository.insertRentedMovie(rentedMovie)

        movieRepository.updateMovieQuantity(rentedMovie.movieId!!, -1)

        return R.string.save_confirmation_message
    }
}

