package bg.tu_varna.sit.si.video_library.data.entities

data class RentedMovie(
    val rentalId: Int = 0,
    val customerId: Int,
    val movieId: Int,
    val rentedDate: String,
    val returnDate: String
)
