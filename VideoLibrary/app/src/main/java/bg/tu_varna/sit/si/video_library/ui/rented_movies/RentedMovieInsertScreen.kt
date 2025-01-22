package bg.tu_varna.sit.si.video_library.ui.rented_movies

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import bg.tu_varna.sit.si.video_library.R
import bg.tu_varna.sit.si.video_library.ui.AppViewModelProvider
import bg.tu_varna.sit.si.video_library.ui.VideoLibraryTopAppBar
import bg.tu_varna.sit.si.video_library.ui.theme.VideoLibraryTheme
import kotlinx.coroutines.launch

@Composable
fun RentedMovieInsertScreen(
    onBackClick: () -> Unit,
    onMenuItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RentedMovieInsertViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val rentedMovieInsertUiState by viewModel.rentedMovieInsertUiState.collectAsState()
    var feedbackMessageId by remember { mutableIntStateOf(R.string.save_confirmation_message) }
    var isDialogVisible by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

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
            buttonText = stringResource(R.string.save),
            modifier = Modifier.padding(innerPadding),
            onValueChange = viewModel::updateUiState,
            onButtonClick = {
                coroutineScope.launch {
                    feedbackMessageId = viewModel.saveRentedMovie()
                    isDialogVisible = true
                }
            }
        )

        if(isDialogVisible) {
            AlertMessageBox(
                messageId = feedbackMessageId,
                onDismiss = {
                    isDialogVisible = false
                    if(feedbackMessageId == R.string.save_confirmation_message) {
                        onBackClick()
                    }
                }
            )
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