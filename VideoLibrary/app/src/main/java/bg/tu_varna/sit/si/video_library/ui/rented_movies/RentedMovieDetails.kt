package bg.tu_varna.sit.si.video_library.ui.rented_movies

data class RentedMovieDetails(
    val rentalId: Int = 0,
    val customerId: Int? = null,
    val movieId: Int? = null,
    val rentedDate: String = "",
    val returnDate: String? = null
)
