package bg.tu_varna.sit.si.video_library.data.repositories

import bg.tu_varna.sit.si.video_library.data.dao.RentedMovieDao
import bg.tu_varna.sit.si.video_library.data.entities.RentedMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

class RentedMovieRepositoryImp(private val rentedMovieDao: RentedMovieDao) : RentedMovieRepository {
    override fun getAllRentedMoviesStream(): Flow<List<RentedMovie>>
    = rentedMovieDao.getAllRentedMovies()

    override fun getRentedMovieStream(id: Int): Flow<RentedMovie>
    = rentedMovieDao.getRentedMovie(id)

    override suspend fun isMovieRented(movieId: Int, excludeRentalId: Int?): Boolean
    = rentedMovieDao.countActiveRentalsExcluding(movieId, excludeRentalId) > 0

    override suspend fun isRentedMovieExists(id: Int): Boolean
    = rentedMovieDao.getRentedMovie(id).firstOrNull() != null

    override suspend fun insertRentedMovie(rentedMovie: RentedMovie)
    = rentedMovieDao.insert(rentedMovie)

    override suspend fun updateRentedMove(rentedMovie: RentedMovie)
    = rentedMovieDao.update(rentedMovie)

    override suspend fun deleteRentedMovie(rentedMovie: RentedMovie)
    = rentedMovieDao.delete(rentedMovie)
}