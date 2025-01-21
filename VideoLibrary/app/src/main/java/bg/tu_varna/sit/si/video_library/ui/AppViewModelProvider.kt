package bg.tu_varna.sit.si.video_library.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import bg.tu_varna.sit.si.video_library.VideoLibraryApplication
import bg.tu_varna.sit.si.video_library.ui.customer.CustomersHomeViewModel
import bg.tu_varna.sit.si.video_library.ui.movie.MoviesHomeViewModel
import bg.tu_varna.sit.si.video_library.ui.rented_movies.RentedMovieDetailsViewModel
import bg.tu_varna.sit.si.video_library.ui.rented_movies.RentedMovieEditViewModel
import bg.tu_varna.sit.si.video_library.ui.rented_movies.RentedMovieInsertViewModel
import bg.tu_varna.sit.si.video_library.ui.rented_movies.RentedMoviesHomeViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            RentedMoviesHomeViewModel(
                videoLibraryApplication().container.rentedMovieRepository
            )
        }

        initializer {
            MoviesHomeViewModel(
                videoLibraryApplication().container.movieRepository
            )
        }

        initializer {
            CustomersHomeViewModel(
                videoLibraryApplication().container.customerRepository
            )
        }

        initializer {
            RentedMovieInsertViewModel(
                videoLibraryApplication().container.rentedMovieRepository,
                videoLibraryApplication().container.movieRepository,
                videoLibraryApplication().container.customerRepository
            )
        }

        initializer {
            RentedMovieEditViewModel(
                savedStateHandle = this.createSavedStateHandle(),
                videoLibraryApplication().container.rentedMovieRepository
            )
        }

        initializer {
            RentedMovieDetailsViewModel(
                savedStateHandle = this.createSavedStateHandle(),
                videoLibraryApplication().container.rentedMovieRepository
            )
        }
    }
}

fun CreationExtras.videoLibraryApplication(): VideoLibraryApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as VideoLibraryApplication)