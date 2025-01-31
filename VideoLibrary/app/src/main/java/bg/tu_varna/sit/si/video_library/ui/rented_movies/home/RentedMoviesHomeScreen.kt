package bg.tu_varna.sit.si.video_library.ui.rented_movies.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import bg.tu_varna.sit.si.video_library.R
import bg.tu_varna.sit.si.video_library.data.entities.RentedMovie
import bg.tu_varna.sit.si.video_library.di.AppViewModelProvider
import bg.tu_varna.sit.si.video_library.ui.common.GenericHomeScreenBody
import bg.tu_varna.sit.si.video_library.ui.navigation.VideoLibraryTopAppBar

//fun fakeData(): List<RentedMovie> = listOf(
//    RentedMovie(
//        rentalId = 1,
//        customerId = 3,
//        movieId = 10,
//        rentedDate = "2024-12-13",
//        returnDate = "2024-12-19"
//    ),
//    RentedMovie(
//        rentalId = 2,
//        customerId = 5,
//        movieId = 12,
//        rentedDate = "2024-12-13",
//        returnDate = "2024-12-19"
//    ),
//    RentedMovie(
//        rentalId = 3,
//        customerId = 3,
//        movieId = 13,
//        rentedDate = "2024-12-19",
//        returnDate = "2024-12-23"
//    )
//)

@Composable
fun RentedMoviesHomeScreen(
    onBackClick: () -> Unit,
    onRecordRowClick: (Int) -> Unit,
    onNewEntryClick: () -> Unit,
    onMenuItemClick: (Int) -> Unit,
    viewModel: RentedMoviesHomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val rentedMovieHomeUiState by viewModel.rentedMovieHomeUiState.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Scaffold(
        topBar = {
            VideoLibraryTopAppBar(
                title = stringResource(R.string.rented_movies),
                showBackButton = true,
                onBackClick = onBackClick,
                onMenuItemClick = onMenuItemClick
            )
        }
    ) {
            innerPadding ->
        RentedMovieHomeScreenBody(
            rentedMovieHomeUiState = rentedMovieHomeUiState,
            searchQuery = searchQuery,
            onSearchQueryChange = viewModel::updateSearchQuery,
            onNewEntryClick = onNewEntryClick,
            onRecordRowClick = onRecordRowClick,
            modifier = Modifier.padding(innerPadding)
        )

    }
}

@Composable
fun RentedMovieHomeScreenBody(
    rentedMovieHomeUiState: RentedMovieHomeUiState,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onNewEntryClick: () -> Unit,
    onRecordRowClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = modifier
            .fillMaxSize()
    ) {
        Button(
            onClick = onNewEntryClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
        ) {
            Text(text = stringResource(R.string.new_record))
        }
        Card(
            border = BorderStroke(width = 1.dp, color = Color.Blue),
            shape = RectangleShape,
            colors = CardDefaults.cardColors(Color.White)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                placeholder = { Text(stringResource(R.string.search)) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.search_icon)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                singleLine = true
            )
            GenericHomeScreenBody(
                itemList = rentedMovieHomeUiState.rentedMoviesList,
                emptyMessage = stringResource(R.string.no_rented_movies_message),
                isLoading = rentedMovieHomeUiState.isLoading,
                modifier = Modifier,
                itemListContent = { rentedMoviesList ->
                    RentedMoviesList(
                        rentedMoviesList = rentedMoviesList,
                        messageId = rentedMovieHomeUiState.messageId,
                        onRecordRowClick = { onRecordRowClick(it.rentalId) }
                    )
                }
            )
        }
    }
}

@Composable
fun RentedMoviesList(
    rentedMoviesList: List<RentedMovie>,
    messageId: Int?,
    onRecordRowClick: (RentedMovie) -> Unit,
    modifier: Modifier = Modifier
) {
    if(rentedMoviesList.isEmpty() && messageId != null) {
        Text(
            text = stringResource(messageId),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
    LazyColumn(
        modifier = modifier
    ) {
        items(items = rentedMoviesList, key = {it.rentalId}) { item ->
            RentedMovieRow(
                rentedMovie = item,
                modifier = Modifier.clickable{onRecordRowClick(item)}
            )
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
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = BorderStroke(width = 1.dp, color = Color.Blue)
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
                    text = "${stringResource(R.string.rental_id)}: ",
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
                    Text(text = "${stringResource(R.string.customer_id)}: ")
                    //Spacer(modifier = Modifier.weight(1f))
                    Text(text = rentedMovie.customerId.toString())
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "${stringResource(R.string.movie_id)}: ")
                    //Spacer(modifier = Modifier.weight(1f))
                    Text(text = rentedMovie.movieId.toString())
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Rental Date:") //TODO: Move this string to strings.xml
                    //Spacer(modifier = Modifier.weight(1f))
                    Text(text = rentedMovie.rentedDate)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Return Date:") //TODO: Move this string to strings.xml
                    //Spacer(modifier = Modifier.weight(1f))
                    Text(text = rentedMovie.returnDate ?: stringResource(R.string.not_returned))
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
//
//@Preview(showBackground = true)
//@Composable
//fun RentedMoviesHomeScreenPreview(){
//    VideoLibraryTheme {
//        RentedMoviesHomeScreen(
//            onBackClick = {},
//            onNewEntryClick = {},
//            onRecordRowClick = {}
//        )
//    }
//}