package bg.tu_varna.sit.si.video_library.ui.rented_movies.state

import bg.tu_varna.sit.si.video_library.data.entities.RentedMovie

data class RentedMovieHomeUiState(
    val rentedMoviesList: List<RentedMovie> = listOf(),
    val isLoading: Boolean = false,
    val messageId: Int? = null
)
