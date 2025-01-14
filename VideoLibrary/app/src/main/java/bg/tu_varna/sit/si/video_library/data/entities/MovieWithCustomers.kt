package bg.tu_varna.sit.si.video_library.data.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class MovieWithCustomers(
    @Embedded
    val movie: Movie,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "customerId",
        associateBy = Junction(RentedMovie::class)
    )
    val customers: List<Customer>
)
