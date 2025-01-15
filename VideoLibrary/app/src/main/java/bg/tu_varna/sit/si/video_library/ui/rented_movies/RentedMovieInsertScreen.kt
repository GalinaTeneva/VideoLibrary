package bg.tu_varna.sit.si.video_library.ui.rented_movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import bg.tu_varna.sit.si.video_library.ui.VideoLibraryTopAppBar
import bg.tu_varna.sit.si.video_library.ui.theme.VideoLibraryTheme

@Composable
fun RentedMovieInsertScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            VideoLibraryTopAppBar(
                title = "Rent a movie",
                showBackButton = true,
                modifier = modifier,
                onBackClick = onBackClick
            )
        }
    ) {
        innerPadding ->
        InputForm(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun InputForm(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),

    ){
        OutlinedTextField(
            value = "test",
            onValueChange = {},
            label = {Text("Movie ID")},
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = "test",
            onValueChange = {},
            label = {Text("Customer ID")},
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = "test",
            onValueChange = {},
            label = {Text("Rented on")},
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = "test",
            onValueChange = {},
            label = {Text("Returned on")},
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

@Composable
@Preview(showBackground = true)
fun RentedMovieInsertScreenPreview() {
    VideoLibraryTheme {
        RentedMovieInsertScreen(
            onBackClick = {},
        )
    }
}