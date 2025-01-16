package bg.tu_varna.sit.si.video_library.data.dao

import androidx.room.Dao
import androidx.room.Query
import bg.tu_varna.sit.si.video_library.data.entities.Genre
import kotlinx.coroutines.flow.Flow

@Dao
interface GenreDao {
    @Query(
        """
            SELECT *
            FROM genre
            ORDER BY type ASC
        """
    )
    fun getAllGenres(): Flow<List<Genre>>
}