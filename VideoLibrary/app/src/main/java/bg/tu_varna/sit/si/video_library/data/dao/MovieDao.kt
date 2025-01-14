package bg.tu_varna.sit.si.video_library.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import bg.tu_varna.sit.si.video_library.data.entities.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movie: Movie)

    @Update()
    suspend fun update(movie: Movie)

    @Delete()
    suspend fun delete(movie: Movie)

    @Query(
        """
            SELECT *
            FROM movies
            WHERE movieId = :id
        """
    )
    fun getMovie(id: Int): Flow<Movie>

    @Query(
        """
            SELECT *
            FROM movies
            ORDER BY title ASC
        """
    )
    fun getAllMovies(): Flow<List<Movie>>
}