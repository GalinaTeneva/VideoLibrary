package bg.tu_varna.sit.si.video_library.data

import bg.tu_varna.sit.si.video_library.data.entities.Customer
import bg.tu_varna.sit.si.video_library.data.entities.Movie
import bg.tu_varna.sit.si.video_library.data.entities.RentedMovie
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking

object DatabaseInitializer {
    //TODO: ADD MORE RECORDS

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
                movieRepository.insertMovie(
                    Movie(2, "The Godfather", "Crime", "Francis Ford Coppola", 3.00, 2)
                )
                movieRepository.insertMovie(
                    Movie(3, "The Dark Knight", "Action", "Christopher Nolan", 4.50, 3)
                )
                movieRepository.insertMovie(
                    Movie(4, "The Lion King", "Animation", "Roger Allers", 3.50, 3)
                )
                movieRepository.insertMovie(
                    Movie(5, "Avatar", "Science Fiction", "James Cameron", 4.50, 4)
                )
                movieRepository.insertMovie(
                    Movie(6, "Gladiator", "Historical", "Ridley Scott", 4.00, 3)
                )

                // Insert Customers
                customerRepository.insertCustomer(
                    Customer(1, "9012121234", "Georgi Ivanov", "Sofia, Graf Ignatiev St. 12")
                )
                customerRepository.insertCustomer(
                    Customer(2, "8801014567", "Maria Georgieva", "Sofia, Vitosha St. 25")
                )
                customerRepository.insertCustomer(
                    Customer(3, "9505057890", "Ivan Petrov", "Sofia, Bulgaria Blvd. 67")
                )
                customerRepository.insertCustomer(
                    Customer(4, "8703031122", "Elena Hristova", "Ruse, Rakovski St. 78")
                )

                // Insert Rented Movies
                rentedMovieRepository.insertRentedMovie(
                    RentedMovie(1, 1, 2, "2024-01-04", "2024-01-15")
                )
                rentedMovieRepository.insertRentedMovie(
                    RentedMovie(2, 3, 3, "2024-01-13", "2024-01-18")
                )
                rentedMovieRepository.insertRentedMovie(
                    RentedMovie(3, 1, 4, "2024-01-15", "2024-01-18")
                )
                rentedMovieRepository.insertRentedMovie(
                    RentedMovie(4, 2, 6, "2024-03-18", "2024-03-25")
                )
                rentedMovieRepository.insertRentedMovie(
                    RentedMovie(5, 2, 2, "2024-06-25", "2024-06-30")
                )
                rentedMovieRepository.insertRentedMovie(
                    RentedMovie(6, 1, 3, "2024-06-25", "2024-06-29")
                )
            }
        }
    }
}