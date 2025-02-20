package bg.tu_varna.sit.si.video_library.ui.movie.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bg.tu_varna.sit.si.video_library.data.repositories.MovieRepository
import bg.tu_varna.sit.si.video_library.ui.movie.state.MovieHomeUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class MoviesHomeViewModel(moviesRepository: MovieRepository) : ViewModel(){
    val movieHomeUiState : StateFlow<MovieHomeUiState> =
        moviesRepository.getAllMoviesStream()
            .map { movies -> MovieHomeUiState(movieList = movies, isLoading = false) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = MovieHomeUiState(isLoading = true)
            )
}
