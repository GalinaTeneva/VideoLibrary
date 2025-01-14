package bg.tu_varna.sit.si.video_library.data.repositories

import bg.tu_varna.sit.si.video_library.data.entities.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovieStream(id: Int): Flow<Movie>
    fun getAllMoviesStream(): Flow<List<Movie>>

    suspend fun insertMovie(movie: Movie)
    suspend fun updateMovie(movie: Movie)
    suspend fun deleteMovie(movie: Movie)
}