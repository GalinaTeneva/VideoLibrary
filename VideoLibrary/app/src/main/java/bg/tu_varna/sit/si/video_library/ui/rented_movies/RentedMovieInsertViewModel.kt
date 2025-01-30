package bg.tu_varna.sit.si.video_library.ui.rented_movies

import androidx.lifecycle.ViewModel
import bg.tu_varna.sit.si.video_library.R
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

    fun updateUiState(rentedMovieDetails: RentedMovieDetails) {
        _rentedMovieInsertUiState.value = _rentedMovieInsertUiState.value.copy(rentedMovieDetails = rentedMovieDetails)
    }

    suspend fun saveRentedMovie(): Int {
        val validationErrorId = validateRentedMovie(
            _rentedMovieInsertUiState.value.rentedMovieDetails,
            movieRepository,
            customerRepository,
            rentedMovieRepository
        )

        if(validationErrorId != null) {
            return validationErrorId
        }

        val rentedMovie = _rentedMovieInsertUiState.value.rentedMovieDetails.toRentMovie()
        rentedMovieRepository.insertRentedMovie(rentedMovie)

        movieRepository.updateMovieQuantity(rentedMovie.movieId!!, -1)

        return R.string.save_confirmation_message
    }

//    private suspend fun validateInput(): Int? {
//        val newRecord = _rentedMovieInsertUiState.value.rentedMovieDetails
//
//        if(newRecord.customerId == null || newRecord.movieId == null || newRecord.rentedDate.isBlank() ) {
//            return R.string.screen_empty_field
//        }
//        if(!movieRepository.isMovieExists(newRecord.movieId)) {
//            return R.string.wrong_movie_id
//        }
//        if(!customerRepository.isCustomerExists(newRecord.customerId)) {
//            return R.string.wrong_customer_id
//        }
//        return null
//    }
}

