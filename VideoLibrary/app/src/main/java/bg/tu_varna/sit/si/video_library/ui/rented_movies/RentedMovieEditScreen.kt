package bg.tu_varna.sit.si.video_library.ui.rented_movies

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import bg.tu_varna.sit.si.video_library.ui.VideoLibraryTopAppBar
import bg.tu_varna.sit.si.video_library.ui.theme.VideoLibraryTheme

@Composable
fun RentedMovieEditScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            VideoLibraryTopAppBar(
                title = "Edit record information",
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
@Preview(showBackground = true)
fun RentedMovieEditScreenPreview() {
    VideoLibraryTheme {
        RentedMovieEditScreen(
            onBackClick = {}
        )
    }
}