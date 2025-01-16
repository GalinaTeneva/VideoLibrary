package bg.tu_varna.sit.si.video_library.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "rentedMovies",
    foreignKeys = [
        ForeignKey(
            entity = Customer::class,
            parentColumns = ["customerId"],
            childColumns = ["customerId"],
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = Movie::class,
            parentColumns = ["movieId"],
            childColumns = ["movieId"],
            onDelete = ForeignKey.SET_NULL
        )
    ]
)
data class RentedMovie(
    @PrimaryKey(autoGenerate = true)
    val rentalId: Int = 0,
    val customerId: Int?,
    val movieId: Int?,
    val rentedDate: String,
    val returnDate: String?
)
