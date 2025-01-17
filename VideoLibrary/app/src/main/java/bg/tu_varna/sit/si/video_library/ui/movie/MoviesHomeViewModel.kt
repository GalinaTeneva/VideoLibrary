package bg.tu_varna.sit.si.video_library.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bg.tu_varna.sit.si.video_library.data.entities.Movie
import bg.tu_varna.sit.si.video_library.data.repositories.MovieRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class MoviesHomeViewModel(moviesRepository: MovieRepository) : ViewModel(){
    val movieHomeUiState : StateFlow<MovieHomeUiState> =
        moviesRepository.getAllMoviesStream()
            .map { MovieHomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = MovieHomeUiState()
            )
}

data class MovieHomeUiState(val movieList: List<Movie> = listOf())