package bg.tu_varna.sit.si.video_library.data.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CustomerWithMovies(
    @Embedded
    val customer: Customer,
    @Relation(
        parentColumn = "customerId",
        entityColumn = "movieId",
        associateBy = Junction(RentedMovie::class)
    )
    val movies: List<Movie>
)
