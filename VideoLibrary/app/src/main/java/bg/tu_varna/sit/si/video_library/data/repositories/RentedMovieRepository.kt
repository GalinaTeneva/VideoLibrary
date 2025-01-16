package bg.tu_varna.sit.si.video_library.data.repositories

import bg.tu_varna.sit.si.video_library.data.entities.RentedMovie
import kotlinx.coroutines.flow.Flow

interface RentedMovieRepository {
    fun getAllRentedMoviesStream(): Flow<List<RentedMovie>>
    fun getRentedMovieStream(id: Int): Flow<RentedMovie>

    suspend fun insertRentedMovie(rentedMovie: RentedMovie)
    suspend fun updateRentedMove(rentedMovie: RentedMovie)
    suspend fun deleteRentedMovie(rentedMovie: RentedMovie)
}