package bg.tu_varna.sit.si.video_library.data.entities

data class Movie(
    val movieId: Int = 0,
    val title: String,
    val genre: String,
    val director: String,
    val price: Double,
    val quantity: Int
)
