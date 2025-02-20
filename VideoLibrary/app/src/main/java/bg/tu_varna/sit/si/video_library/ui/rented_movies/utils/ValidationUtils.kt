package bg.tu_varna.sit.si.video_library.ui.rented_movies.utils

import bg.tu_varna.sit.si.video_library.R
import bg.tu_varna.sit.si.video_library.data.repositories.CustomerRepository
import bg.tu_varna.sit.si.video_library.data.repositories.MovieRepository
import bg.tu_varna.sit.si.video_library.data.repositories.RentedMovieRepository
import bg.tu_varna.sit.si.video_library.ui.rented_movies.state.RentedMovieFormData
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

suspend fun validateRentedMovie(
    input: RentedMovieFormData,
    movieRepository: MovieRepository,
    customerRepository: CustomerRepository,
    rentedMovieRepository: RentedMovieRepository,
    excludeRentalId: Int? = null
): Int? {

    //Check if the required fields are missing
    if(input.customerId == null || input.movieId == null || input.rentedDate.isBlank()) {
        return R.string.screen_empty_field
    }

    //Validate rented date
    if(!isDateFormatValid(input.rentedDate)) {
        return R.string.invalid_rented_date
    }

    //Validate return date
    if(!input.returnDate.isNullOrBlank()) {
        if(!isDateFormatValid(input.returnDate)) {
            return R.string.invalid_return_date
        }

        //Check if return date is before rented date
        if(!isReturnDateValid(input.rentedDate, input.returnDate)) {
            return R.string.return_date_before_rented_date
        }
    }

    //Validate MovieID
    if(!movieRepository.isMovieExists(input.movieId)) {
        return R.string.wrong_movie_id
    }

    //Validate CustomerID
    if(!customerRepository.isCustomerExists(input.customerId)) {
        return R.string.wrong_customer_id
    }

    if(rentedMovieRepository.isMovieRented(input.movieId, excludeRentalId)) {
        return R.string.movie_already_rented
    }

    return null
}

fun isDateFormatValid(date: String?): Boolean {
    return try {
        LocalDate.parse(date, formatter)
        true
    } catch (e: DateTimeParseException) {
        false
    }
}

fun isReturnDateValid(rentedDate: String, returnDate: String?): Boolean {
    return try {
        val rentedLocalDate = LocalDate.parse(rentedDate, formatter)
        val returnLocalDate = LocalDate.parse(returnDate, formatter)

        !returnLocalDate.isBefore(rentedLocalDate)
    } catch (e: DateTimeParseException) {
        false
    }
}