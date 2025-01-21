package bg.tu_varna.sit.si.video_library.ui.rented_movies

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import bg.tu_varna.sit.si.video_library.ui.AppViewModelProvider
import bg.tu_varna.sit.si.video_library.ui.VideoLibraryTopAppBar
import bg.tu_varna.sit.si.video_library.ui.theme.VideoLibraryTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RentedMovieInsertScreen(
    onBackClick: () -> Unit,
    onMenuItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RentedMovieInsertViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val rentedMovieInsertUiState by viewModel.rentedMovieInsertUiState.collectAsState()
    val feedbackMessage by viewModel.feedbackMessage.collectAsState()

    val coroutineScope = rememberCoroutineScope()
    var isDialogVisible by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            VideoLibraryTopAppBar(
                title = stringResource((R.string.rent_movie_bar_title)),
                showBackButton = true,
                modifier = modifier,
                onBackClick = onBackClick,
                onMenuItemClick = onMenuItemClick
            )
        }
    ) {
        innerPadding ->
        InputForm(
            rentedMovieUiState = rentedMovieInsertUiState,
            buttonText = "Save",
            modifier = Modifier.padding(innerPadding),
            onValueChange = viewModel::updateUiState,
            onButtonClick = {
                coroutineScope.launch {
                    viewModel.saveRentedMovie()
                    isDialogVisible = true
                }
            }
        )

        if(isDialogVisible) {
            BasicAlertDialog(
                onDismissRequest = {isDialogVisible = false},
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
                       text = "Title",
                       style = MaterialTheme.typography.headlineMedium,
                       modifier = Modifier.align(Alignment.Start)
                   )
                   Text(
                       text = feedbackMessage,
                       style = MaterialTheme.typography.bodyMedium,
                       textAlign = TextAlign.Center
                   )
               }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun RentedMovieInsertScreenPreview() {
    VideoLibraryTheme {
        RentedMovieInsertScreen(
            onBackClick = {},
            onMenuItemClick = {}
        )
    }
}