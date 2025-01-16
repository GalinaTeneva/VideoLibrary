package bg.tu_varna.sit.si.video_library.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import bg.tu_varna.sit.si.video_library.data.entities.RentedMovie
import kotlinx.coroutines.flow.Flow

@Dao
interface RentedMovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(rentedMovie: RentedMovie)

    @Update
    suspend fun update(rentedMovie: RentedMovie)

    @Delete
    suspend fun delete(rentedMovie: RentedMovie)

    @Query(
        """
            SELECT *
            FROM rentedMovies
            WHERE rentalId = :id
        """
    )
    fun getRentedMovie(id: Int): Flow<RentedMovie>

    @Query(
        """
            SELECT *
            FROM rentedMovies
        """
    )
    fun getAllRentedMovies(): Flow<List<RentedMovie>>
}