package bg.tu_varna.sit.si.video_library.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import bg.tu_varna.sit.si.video_library.R
import bg.tu_varna.sit.si.video_library.ui.customer.CustomerHomeScreen
import bg.tu_varna.sit.si.video_library.ui.home.VideoLibraryHomeScreenWithAppBar
import bg.tu_varna.sit.si.video_library.ui.movie.MovieHomeScreen
import bg.tu_varna.sit.si.video_library.ui.rented_movies.RentedMovieDetailsScreen
import bg.tu_varna.sit.si.video_library.ui.rented_movies.RentedMovieEditScreen
import bg.tu_varna.sit.si.video_library.ui.rented_movies.RentedMovieInsertScreen
import bg.tu_varna.sit.si.video_library.ui.rented_movies.RentedMoviesHomeScreen

sealed class Screen(val route: String) {
    object VideoLibraryHome : Screen("video_library_home")
    object MoviesHome : Screen("movies_home")
    object CustomersHome : Screen("customer_home")
    object RentedMoviesHome : Screen ("rented_movies_home")
    object RentedMovieInsert : Screen ("rented_movie_insert")
    object RentedMovieEdit : Screen ("rented_movie_edit/{rentedMovieId}") {
        fun createRoute(rentedMovieId: Int): String = "rented_movie_edit/$rentedMovieId"
    }
    object RentedMovieDetails : Screen ("rented_movie_details/{rentedMovieId}") {
        fun createRoute(rentedMovieId: Int): String = "rented_movie_details/$rentedMovieId"
    }
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
        val onMenuItemClick: (Int) -> Unit = { menuItem ->
            val currentDestination = navController.currentBackStackEntry?.destination?.route
            val targetDestination: String = when (menuItem) {
                R.string.movies -> Screen.MoviesHome.route
                R.string.customers -> Screen.CustomersHome.route
                R.string.rented_movies -> Screen.RentedMoviesHome.route
                else -> ""
            }
            if(currentDestination != targetDestination) {
                navController.navigate(targetDestination)
            }
        }

        composable(route = Screen.VideoLibraryHome.route) {
            VideoLibraryHomeScreenWithAppBar(
                onNavigateToMovies = {navController.navigate(Screen.MoviesHome.route)},
                onNavigateToCustomers = {navController.navigate(Screen.CustomersHome.route)},
                onNavigateToRentedMovies = {navController.navigate(Screen.RentedMoviesHome.route)}
            )
        }
        composable(route = Screen.MoviesHome.route) {
            MovieHomeScreen(
                onBackClick = {navController.navigateUp()},
                onMenuItemCLick = onMenuItemClick
            )
        }
        composable(route = Screen.CustomersHome.route) {
            CustomerHomeScreen(
                onBackClick = {navController.navigateUp()},
                onMenuItemClick = onMenuItemClick
            )
        }
        composable(route = Screen.RentedMoviesHome.route) {
            RentedMoviesHomeScreen(
                onBackClick = {navController.navigateUp()},
                onMenuItemClick = onMenuItemClick,
                onNewEntryClick = {navController.navigate(Screen.RentedMovieInsert.route)},
                onRecordRowClick = {rentedMovieId -> navController.navigate(Screen.RentedMovieDetails.createRoute(rentedMovieId))}
            )
        }
        composable(route = Screen.RentedMovieInsert.route) {
            RentedMovieInsertScreen(
                onBackClick = {navController.navigateUp()},
                onMenuItemClick = onMenuItemClick
            )
        }
        composable(
            route = Screen.RentedMovieEdit.route,
            arguments = listOf(navArgument("rentedMovieId") {type = NavType.IntType})
        ) {
            RentedMovieEditScreen(
                onBackClick = {navController.navigateUp()},
                onMenuItemClick = onMenuItemClick
            )
        }
        composable(
            route = Screen.RentedMovieDetails.route,
            arguments = listOf(navArgument("rentedMovieId") {type = NavType.IntType})
        ) {
            RentedMovieDetailsScreen(
                onBackClick = {navController.navigateUp()},
                onNavigateToEdit = {rentedMovieId -> navController.navigate(Screen.RentedMovieEdit.createRoute(rentedMovieId))},
                onMenuItemClick = onMenuItemClick
            )
        }
    }
}