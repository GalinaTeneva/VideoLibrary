package bg.tu_varna.sit.si.video_library.ui.movie.state

import bg.tu_varna.sit.si.video_library.data.entities.Movie

data class MovieHomeUiState(
    val movieList: List<Movie> = listOf(),
    val isLoading: Boolean = false
)
