package bg.tu_varna.sit.si.video_library.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import bg.tu_varna.sit.si.video_library.ui.VideoLibraryTopAppBar
import bg.tu_varna.sit.si.video_library.ui.theme.VideoLibraryTheme

@Composable
fun VideoLibraryHomeScreenWithAppBar(
    onNavigateToMovies: () -> Unit,
    onNavigateToCustomers: () -> Unit,
    onNavigateToRentedMovies: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            VideoLibraryTopAppBar(
                title = "Video Library App",
                modifier = modifier
            )
        }
    ) {
        innerPadding ->
        VideoLibraryHomeScreenBody(
            onNavigateToMovies = onNavigateToMovies,
            onNavigateToCustomers = onNavigateToCustomers,
            onNavigateToRentedMovies = onNavigateToRentedMovies,
            Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun VideoLibraryHomeScreenBody(
    onNavigateToMovies: () -> Unit,
    onNavigateToCustomers: () -> Unit,
    onNavigateToRentedMovies: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(top = 25.dp, bottom = 25.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "WELCOME TO VIDEO LIBRARY",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        Button(onClick = onNavigateToMovies) {
            Text(text = "MOVIES")
        }
        Button(onClick = onNavigateToCustomers) {
            Text(text = "CUSTOMERS")
        }
        Button(onClick = onNavigateToRentedMovies) {
            Text(text = "RENTED MOVIES")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun VideoLibraryHomeScreenPreview() {
    VideoLibraryTheme {
        VideoLibraryHomeScreenBody(
            onNavigateToMovies = {},
            onNavigateToCustomers = {},
            onNavigateToRentedMovies = {}
        )
    }
}