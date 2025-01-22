package bg.tu_varna.sit.si.video_library.ui.rented_movies

import bg.tu_varna.sit.si.video_library.R
import bg.tu_varna.sit.si.video_library.data.repositories.CustomerRepository
import bg.tu_varna.sit.si.video_library.data.repositories.MovieRepository

suspend fun validateRentedMovie(
    newRecord: RentedMovieDetails,
    movieRepository: MovieRepository,
    customerRepository: CustomerRepository
): Int? {

    if(newRecord.customerId == null || newRecord.movieId == null || newRecord.rentedDate.isBlank()) {
        return R.string.screen_empty_field
    }

    if(!movieRepository.isMovieExists(newRecord.movieId)) {
        return R.string.wrong_movie_id
    }

    if(!customerRepository.isCustomerExists(newRecord.customerId)) {
        return R.string.wrong_customer_id
    }

    return null
}