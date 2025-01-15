package bg.tu_varna.sit.si.video_library.ui.rented_movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import bg.tu_varna.sit.si.video_library.data.entities.RentedMovie
import bg.tu_varna.sit.si.video_library.ui.GenericHomeScreenBody
import bg.tu_varna.sit.si.video_library.ui.VideoLibraryTopAppBar
import bg.tu_varna.sit.si.video_library.ui.theme.VideoLibraryTheme

fun fakeData(): List<RentedMovie> = listOf(
    RentedMovie(
        rentalId = 1,
        customerId = 3,
        movieId = 10,
        rentedDate = "2024-12-13",
        returnDate = "2024-12-19"
    ),
    RentedMovie(
        rentalId = 2,
        customerId = 5,
        movieId = 12,
        rentedDate = "2024-12-13",
        returnDate = "2024-12-19"
    ),
    RentedMovie(
        rentalId = 3,
        customerId = 3,
        movieId = 13,
        rentedDate = "2024-12-19",
        returnDate = "2024-12-23"
    )
)

@Composable
fun RentedMoviesHomeScreen(
    onBackClick: () -> Unit,
    onNewEntryClick: () -> Unit
) {
    Scaffold(
        topBar = {
            VideoLibraryTopAppBar(
                title = "Rented Movies",
                showBackButton = true,
                onBackClick = onBackClick
            )
        }
    ) {
            innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Button(
                onClick = onNewEntryClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "New Entry")
            }
            GenericHomeScreenBody(
                itemList = fakeData(),
                emptyMessage = "Sorry!\n\nThere are no rented movies yet.",
                modifier = Modifier,
                itemListContent = { rentedMoviesList ->
                    RentedMoviesList(rentedMoviesList = rentedMoviesList)
                }
            )
        }
    }
}

@Composable
fun RentedMoviesList(
    rentedMoviesList: List<RentedMovie>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(rentedMoviesList) { item ->
            RentedMovieRow(
                rentedMovie = item)
        }
    }
}

@Composable
fun RentedMovieRow(
    rentedMovie: RentedMovie,
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
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Rental ID:",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = rentedMovie.rentalId.toString(),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.SemiBold,
                )
            }
            Column(
                modifier = Modifier.padding(top = 4.dp, start = 8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Client ID: ")
                    //Spacer(modifier = Modifier.weight(1f))
                    Text(text = rentedMovie.customerId.toString())
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Movie ID:")
                    //Spacer(modifier = Modifier.weight(1f))
                    Text(text = rentedMovie.movieId.toString())
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Rental Date:")
                    //Spacer(modifier = Modifier.weight(1f))
                    Text(text = rentedMovie.rentedDate)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Return Date:")
                    //Spacer(modifier = Modifier.weight(1f))
                    Text(text = rentedMovie.returnDate)
                }
            }
//            Button(
//                onClick = {},
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text(text = "Rented Movies")
//            }
        }
    }
}

//@Preview
//@Composable
//fun RentedMovieRowPreview() {
//    VideoLibraryTheme {
//        RentedMovieRow(fakeData()[0])
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun RentedMoviesListPreview() {
//    VideoLibraryTheme {
//        RentedMoviesList(fakeData())
//    }
//}

@Preview(showBackground = true)
@Composable
fun RentedMoviesHomeScreenPreview(){
    VideoLibraryTheme {
        RentedMoviesHomeScreen(
            onBackClick = {},
            onNewEntryClick = {}
        )
    }
}