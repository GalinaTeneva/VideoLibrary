package bg.tu_varna.sit.si.video_library.ui.rented_movies

import bg.tu_varna.sit.si.video_library.R
import bg.tu_varna.sit.si.video_library.data.repositories.CustomerRepository
import bg.tu_varna.sit.si.video_library.data.repositories.MovieRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

suspend fun validateRentedMovie(
    input: RentedMovieDetails,
    movieRepository: MovieRepository,
    customerRepository: CustomerRepository
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
    if(input.returnDate != null) {
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