package bg.tu_varna.sit.si.video_library.ui.rented_movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import bg.tu_varna.sit.si.video_library.data.entities.RentedMovie
import bg.tu_varna.sit.si.video_library.ui.theme.VideoLibraryTheme

@Composable
fun InputForm(
    rentedMovieUiState: RentedMovieUiState,
    modifier: Modifier = Modifier,
    onValueChange: (RentedMovieDetails) -> Unit
) {
    val rentedMovieDetails = rentedMovieUiState.rentedMovieDetails

    Column(
        modifier = modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),

        ){
        OutlinedTextField(
            value = rentedMovieDetails.movieId?.toString() ?: "",
            onValueChange = {
                val newMovieId = it.toIntOrNull()
                onValueChange(rentedMovieDetails.copy(movieId = newMovieId))
            },
            label = { Text("Movie ID") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = rentedMovieDetails.customerId?.toString() ?: "",
            onValueChange = {
                val newCustomerId = it.toIntOrNull()
                onValueChange(rentedMovieDetails.copy(customerId = newCustomerId))
            },
            label = { Text("Customer ID") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = rentedMovieDetails.rentedDate,
            onValueChange = {
                onValueChange(rentedMovieDetails.copy(rentedDate = it))
            },
            label = { Text("Rented on") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = rentedMovieDetails.returnDate?.toString() ?: "",
            onValueChange = {
                onValueChange(rentedMovieDetails.copy(returnDate = it))
            },
            label = { Text("Returned on") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }
    }
}

//@Composable
//@Preview(showBackground = true)
//fun InputFormPreview() {
//    VideoLibraryTheme {
//        InputForm()
//    }
//}