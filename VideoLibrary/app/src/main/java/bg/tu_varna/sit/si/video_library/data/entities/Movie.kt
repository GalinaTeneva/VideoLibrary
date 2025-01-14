package bg.tu_varna.sit.si.video_library.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val movieId: Int = 0,
    val title: String,
    val genre: String,
    val director: String,
    val price: Double,
    val quantity: Int
)
