package bg.tu_varna.sit.si.video_library.data.repositories

import bg.tu_varna.sit.si.video_library.data.dao.RentedMovieDao
import bg.tu_varna.sit.si.video_library.data.entities.RentedMovie
import kotlinx.coroutines.flow.Flow

class RentedMovieRepositoryImp(private val rentedMovieDao: RentedMovieDao) : RentedMovieRepository {
    override fun getAllRentedMoviesStream(): Flow<List<RentedMovie>>
    = rentedMovieDao.getAllRentedMovies()

    override fun getRentedMovieStream(id: Int): Flow<RentedMovie>
    = rentedMovieDao.getRentedMovie(id)

    override suspend fun insertRentedMovie(rentedMovie: RentedMovie)
    = rentedMovieDao.insert(rentedMovie)

    override suspend fun updateRentedMove(rentedMovie: RentedMovie)
    = rentedMovieDao.update(rentedMovie)

    override suspend fun deleteRentedMovie(rentedMovie: RentedMovie)
    = rentedMovieDao.delete(rentedMovie)
}