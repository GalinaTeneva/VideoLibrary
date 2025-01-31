package bg.tu_varna.sit.si.video_library.ui.rented_movies.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import bg.tu_varna.sit.si.video_library.R
import bg.tu_varna.sit.si.video_library.data.entities.RentedMovie
import bg.tu_varna.sit.si.video_library.di.AppViewModelProvider
import bg.tu_varna.sit.si.video_library.ui.navigation.VideoLibraryTopAppBar
import bg.tu_varna.sit.si.video_library.ui.rented_movies.state.RentedMovieUiState
import bg.tu_varna.sit.si.video_library.ui.rented_movies.state.toRentMovie
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
fun RentedMovieDetailsScreen(
    onBackClick: () -> Unit,
    onNavigateToEdit: (Int) -> Unit,
    onMenuItemClick: (Int) -> Unit,
    viewModel: RentedMovieDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val rentedMovieDetailsUiState = viewModel.rentedMovieDetailsUiState.collectAsState()

    Scaffold(
        topBar = {
            VideoLibraryTopAppBar(
                title = stringResource(R.string.rent_record_details),
                showBackButton = true,
                onBackClick = onBackClick,
                onMenuItemClick = onMenuItemClick
            )
        }
    ) {
        innerPadding ->
        RentedMovieDetailsBody(
            rentedMovieDetailsUiState = rentedMovieDetailsUiState.value,
            onDelete = {
                viewModel.deleteRecord()
                onBackClick()
            },
            onEdit = onNavigateToEdit,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun RentedMovieDetailsBody(
    rentedMovieDetailsUiState: RentedMovieUiState,
    onDelete: () -> Unit,
    onEdit: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(16.dp)
    ){
        val rentedMovie = rentedMovieDetailsUiState.rentedMovieFormData.toRentMovie()
        var isConfirmationRequired by rememberSaveable {mutableStateOf(false)}

        RentedMovieDetailsCard(
            rentedMovie = rentedMovie
        )
        Button(
            onClick = {onEdit(rentedMovie.rentalId)},
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = stringResource(R.string.edit))
        }
        Button(
            onClick = { isConfirmationRequired = true },
            colors = ButtonColors(Color.DarkGray, Color.White, Color.Gray, Color.Gray),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = stringResource(R.string.delete))
        }

        if(isConfirmationRequired) {
            DeleteConfirmationDialog(
                onConfirmDelete = {
                    isConfirmationRequired = false
                    onDelete()
                },
                onCancelDelete = {isConfirmationRequired = false}
            )
        }
    }
}

@Composable
fun RentedMovieDetailsCard(
    rentedMovie: RentedMovie,
    modifier: Modifier = Modifier
    ) {
    Card()
    {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier.padding(8.dp)
        ) {
            DetailsRow(
                label = stringResource(R.string.rental_id),
                fieldValue = rentedMovie.rentalId.toString()
            )
            DetailsRow(
                label = stringResource(R.string.movie_id),
                fieldValue = rentedMovie.movieId.toString()
            )
            DetailsRow(
                label = stringResource(R.string.customer_id),
                fieldValue = rentedMovie.customerId.toString()
            )
            DetailsRow(
                label = stringResource(R.string.rented_on),
                fieldValue = rentedMovie.rentedDate
            )
            DetailsRow(
                label = stringResource(R.string.returned_on),
                fieldValue = rentedMovie.returnDate ?: ""
            )
        }
    }
}

@Composable
fun DetailsRow(
    label: String,
    fieldValue: String,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp)
    ) {
        Text(text = label)
        Text(text = fieldValue)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DeleteConfirmationDialog(
    onConfirmDelete: () -> Unit,
    onCancelDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    BasicAlertDialog(
        onDismissRequest = {},
        modifier = modifier.background(color = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.delete_confirmation_title),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.align(Alignment.Start)
            )
            Text(
                text = stringResource(R.string.delete_confirmation_text),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
                )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    onClick = onCancelDelete
                ) {
                    Text("NO")
                }
                Spacer(modifier = Modifier.width(8.dp))
                TextButton(
                    onClick = onConfirmDelete
                ) {
                    Text("YES")
                }
            }
        }
    }
}

//@Composable
//@Preview(showBackground = true)
//fun RentedMovieDetailsCardPreview() {
//    VideoLibraryTheme {
//        RentedMovieDetailsCard(rentedMovie = fakeData()[0])
//    }
//}
//
//@Composable
//@Preview(showBackground = true)
//fun RentedMovieDetailsBodyPreview() {
//    VideoLibraryTheme {
//        RentedMovieDetailsBody()
//    }
//}

@Composable
@Preview(showBackground = true)
fun RentedMovieDetailsScreenPreview() {
    VideoLibraryTheme {
        RentedMovieDetailsScreen(onBackClick = {}, onNavigateToEdit = {}, onMenuItemClick = {})
    }
}

@Composable
@Preview(showBackground = true)
fun DeleteConfirmationDialogPreview() {
    VideoLibraryTheme {
        DeleteConfirmationDialog(onCancelDelete = {}, onConfirmDelete = {})
    }
}