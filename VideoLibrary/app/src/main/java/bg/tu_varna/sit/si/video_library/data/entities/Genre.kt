package bg.tu_varna.sit.si.video_library.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genre")
data class Genre(
    @PrimaryKey(autoGenerate = true)
    val genreId: Int = 0,
    val type: String
)
