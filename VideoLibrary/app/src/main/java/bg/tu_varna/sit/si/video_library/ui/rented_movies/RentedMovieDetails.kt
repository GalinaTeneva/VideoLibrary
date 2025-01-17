package bg.tu_varna.sit.si.video_library.ui.rented_movies

import bg.tu_varna.sit.si.video_library.data.entities.RentedMovie

data class RentedMovieDetails(
    val rentalId: Int = 0,
    val customerId: Int? = null,
    val movieId: Int? = null,
    val rentedDate: String = "",
    val returnDate: String? = null
)

fun RentedMovieDetails.toRentMovie(): RentedMovie = RentedMovie(
    rentalId = rentalId,
    customerId = customerId,
    movieId = movieId,
    rentedDate = rentedDate,
    returnDate = returnDate
)

fun RentedMovie.toRentedMovieDetails(): RentedMovieDetails = RentedMovieDetails(
    rentalId = rentalId,
    customerId = customerId,
    movieId = movieId,
    rentedDate = rentedDate,
    returnDate = returnDate
)