package bg.tu_varna.sit.si.video_library.ui.rented_movies

import bg.tu_varna.sit.si.video_library.R
import bg.tu_varna.sit.si.video_library.data.repositories.CustomerRepository
import bg.tu_varna.sit.si.video_library.data.repositories.MovieRepository

suspend fun validateRentedMovie(
    input: RentedMovieDetails,
    movieRepository: MovieRepository,
    customerRepository: CustomerRepository
): Int? {

    if(input.customerId == null || input.movieId == null || input.rentedDate.isBlank()) {
        return R.string.screen_empty_field
    }

    if(!movieRepository.isMovieExists(input.movieId)) {
        return R.string.wrong_movie_id
    }

    if(!customerRepository.isCustomerExists(input.customerId)) {
        return R.string.wrong_customer_id
    }

    return null
}