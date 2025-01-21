package bg.tu_varna.sit.si.video_library.data.repositories

import bg.tu_varna.sit.si.video_library.data.dao.MovieDao
import bg.tu_varna.sit.si.video_library.data.entities.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

class MovieRepositoryImp(private val movieDao: MovieDao) : MovieRepository {
    override fun getAllMoviesStream(): Flow<List<Movie>> = movieDao.getAllMovies()

    override fun getMovieStream(id: Int): Flow<Movie> = movieDao.getMovie(id)

    override suspend fun isMovieExists(id: Int): Boolean = movieDao.getMovie(id).firstOrNull() != null

    override suspend fun insertMovie(movie: Movie) = movieDao.insert(movie)

    override suspend fun updateMovie(movie: Movie) = movieDao.update(movie)

    override suspend fun deleteMovie(movie: Movie) = movieDao.delete(movie)

}