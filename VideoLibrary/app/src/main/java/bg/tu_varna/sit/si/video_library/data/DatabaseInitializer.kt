package bg.tu_varna.sit.si.video_library.data

import bg.tu_varna.sit.si.video_library.data.entities.Customer
import bg.tu_varna.sit.si.video_library.data.entities.Movie
import bg.tu_varna.sit.si.video_library.data.entities.RentedMovie
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking

object DatabaseInitializer {

    fun initializeData(container: AppDataContainer) {
        runBlocking {
            val rentedMovieRepository = container.rentedMovieRepository
            val movieRepository = container.movieRepository
            val customerRepository = container.customerRepository

            // Check if Rented Movies are already initialized
            if (rentedMovieRepository.getAllRentedMoviesStream().firstOrNull().isNullOrEmpty()) {
                // Insert Movies
                movieRepository.insertMovie(
                    Movie(1, "The Shawshank Redemption", "Drama", "Frank Darabont", 3.50, 10)
                )

                // Insert Customers
                customerRepository.insertCustomer(
                    Customer(1, "123456789", "John Doe", "123 Main St")
                )

                // Insert Rented Movies
                rentedMovieRepository.insertRentedMovie(
                    RentedMovie(1, 1, 1, "2024-01-01", "2024-01-15")
                )
            }
        }
    }
}