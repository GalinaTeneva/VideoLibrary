package bg.tu_varna.sit.si.video_library.ui.rented_movies

import androidx.lifecycle.ViewModel
import bg.tu_varna.sit.si.video_library.data.repositories.CustomerRepository
import bg.tu_varna.sit.si.video_library.data.repositories.MovieRepository
import bg.tu_varna.sit.si.video_library.data.repositories.RentedMovieRepository
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

    private val _feedbackMessage = MutableStateFlow("")
    val feedbackMessage: StateFlow<String> = _feedbackMessage.asStateFlow()

    fun updateUiState(rentedMovieDetails: RentedMovieDetails) {
        _rentedMovieInsertUiState.value = _rentedMovieInsertUiState.value.copy(rentedMovieDetails = rentedMovieDetails)
    }

    suspend fun saveRentedMovie() {
        val validationError = validateInput()
        if(validationError != "") {
            _feedbackMessage.value = validationError
            return
        }

        val rentedMovie = _rentedMovieInsertUiState.value.rentedMovieDetails.toRentMovie()
        rentedMovieRepository.insertRentedMovie(rentedMovie)
        _feedbackMessage.value = "You have added a new record."
    }

    private suspend fun validateInput(): String {
        val newRecord = _rentedMovieInsertUiState.value.rentedMovieDetails

        if(newRecord.customerId == null || newRecord.movieId == null || newRecord.rentedDate.isBlank() ) {
            return "Please, fill in Customer ID, Movie ID and Rental Date!"
        }
        if(!movieRepository.isMovieExists(newRecord.movieId)) {
            return "Movie ID ${newRecord.movieId} does not exists."
        }
        if(!customerRepository.isCustomerExists(newRecord.customerId)) {
            return "Customer ID ${newRecord.customerId} does not exists."
        }
        return ""
    }
}

