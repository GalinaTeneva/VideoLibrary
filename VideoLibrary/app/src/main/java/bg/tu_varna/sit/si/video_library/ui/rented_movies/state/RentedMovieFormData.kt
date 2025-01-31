package bg.tu_varna.sit.si.video_library.ui.rented_movies.state

import bg.tu_varna.sit.si.video_library.data.entities.RentedMovie

data class RentedMovieFormData(
    val rentalId: Int = 0,
    val customerId: Int? = null,
    val movieId: Int? = null,
    val rentedDate: String = "",
    val returnDate: String? = null
)

fun RentedMovieFormData.toRentMovie(): RentedMovie = RentedMovie(
    rentalId = rentalId,
    customerId = customerId,
    movieId = movieId,
    rentedDate = rentedDate,
    returnDate = returnDate
)

fun RentedMovie.toRentedMovieFormData(): RentedMovieFormData = RentedMovieFormData(
    rentalId = rentalId,
    customerId = customerId,
    movieId = movieId,
    rentedDate = rentedDate,
    returnDate = returnDate
)