package bg.tu_varna.sit.si.video_library.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import bg.tu_varna.sit.si.video_library.ui.customer.CustomerHomeScreen
import bg.tu_varna.sit.si.video_library.ui.home.VideoLibraryHomeScreenWithAppBar
import bg.tu_varna.sit.si.video_library.ui.movie.MovieHomeScreen
import bg.tu_varna.sit.si.video_library.ui.rented_movies.RentedMovieInsertScreen
import bg.tu_varna.sit.si.video_library.ui.rented_movies.RentedMoviesHomeScreen

sealed class Screen(val route: String) {
    object VideoLibraryHome : Screen("video_library_home")
    object MoviesHome : Screen("movies_home")
    object CustomersHome : Screen("customer_home")
    object RentedMoviesHome : Screen ("rented_movies_home")
    object RentedMovieInsert : Screen ("rented_movie_insert")
}

@Composable
fun VideoLibraryNavHost (
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.VideoLibraryHome.route,
        modifier = modifier
    ) {
        composable(route = Screen.VideoLibraryHome.route) {
            VideoLibraryHomeScreenWithAppBar(
                onNavigateToMovies = {navController.navigate(Screen.MoviesHome.route)},
                onNavigateToCustomers = {navController.navigate(Screen.CustomersHome.route)},
                onNavigateToRentedMovies = {navController.navigate(Screen.RentedMoviesHome.route)}
            )
        }
        composable(route = Screen.MoviesHome.route) {
            MovieHomeScreen(
                onBackClick = {navController.navigateUp()}
            )
        }
        composable(route = Screen.CustomersHome.route) {
            CustomerHomeScreen(
                onBackClick = {navController.navigateUp()}
            )
        }
        composable(route = Screen.RentedMoviesHome.route) {
            RentedMoviesHomeScreen(
                onBackClick = {navController.navigateUp()},
                onNewEntryClick = {navController.navigate(Screen.RentedMovieInsert)}
            )
        }
        composable(route = Screen.RentedMovieInsert.route) {
            RentedMovieInsertScreen(
                onBackClick = {navController.navigateUp()}
            )
        }
    }
}