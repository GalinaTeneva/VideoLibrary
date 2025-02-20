package bg.tu_varna.sit.si.video_library.ui.movie.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import bg.tu_varna.sit.si.video_library.R
import bg.tu_varna.sit.si.video_library.data.entities.Movie
import bg.tu_varna.sit.si.video_library.di.AppViewModelProvider
import bg.tu_varna.sit.si.video_library.ui.common.GenericHomeScreenBody
import bg.tu_varna.sit.si.video_library.ui.navigation.VideoLibraryTopAppBar

//fun fakeMovieList(): List<Movie> {
//    return listOf(
//        Movie(
//            movieId = 1,
//            title = "Harry Potter",
//            genre = "Fantasy",
//            director = "Chris Columbus",
//            price = 3.2,
//            quantity = 3
//        ),
//        Movie(
//            movieId = 2,
//            title = "Back to the Future",
//            genre = "sci-fi",
//            director = "Robert Zemeckis",
//            price = 2.50,
//            quantity = 4
//        )
//    )
//}

@Composable
fun MovieHomeScreen(
    onBackClick: () -> Unit,
    onMenuItemCLick: (Int) -> Unit,
    viewModel: MoviesHomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val moviesUiState by viewModel.movieHomeUiState.collectAsState()

    Scaffold(
        topBar = {
            VideoLibraryTopAppBar(
                title = stringResource(R.string.movies),
                showBackButton = true,
                onBackClick = onBackClick,
                onMenuItemClick = onMenuItemCLick
            )
        }
    ) {
            innerPadding ->
        GenericHomeScreenBody(
            itemList = moviesUiState.movieList,
            emptyMessage = stringResource(R.string.no_movies_message),
            isLoading = moviesUiState.isLoading,
            modifier = Modifier.padding(innerPadding),
            itemListContent = { movieList ->
            MoviesList(movieList = movieList)
        }
        )
    }
}

@Composable
fun MoviesList(
    movieList: List<Movie>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(movieList) { item ->
            MovieItem(
                movie = item)
        }
    }
}

@Composable
fun MovieItem(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = movie.title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.SemiBold
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "${stringResource(R.string.genre)}: ${movie.genre}")
                Text(text = "${stringResource(R.string.director)}: ${movie.director}")
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "${stringResource(R.string.available)}: ${movie.quantity}")
                Text(text = "${stringResource(R.string.rental_price)}: ${movie.price} lv")
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun MovieItemPreview() {
//    VideoLibraryTheme {
//        MovieItem(fakeMovieList()[0])
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun MovieListPreview() {
//    VideoLibraryTheme {
//        MoviesList(fakeMovieList())
//    }
//}