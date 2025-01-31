package bg.tu_varna.sit.si.video_library.ui.rented_movies.edit


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import bg.tu_varna.sit.si.video_library.R
import bg.tu_varna.sit.si.video_library.di.AppViewModelProvider
import bg.tu_varna.sit.si.video_library.ui.navigation.VideoLibraryTopAppBar
import bg.tu_varna.sit.si.video_library.ui.common.InputForm
import bg.tu_varna.sit.si.video_library.ui.common.AlertMessageBox
import kotlinx.coroutines.launch

@Composable
fun RentedMovieEditScreen(
    onBackClick: () -> Unit,
    onMenuItemClick:(Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RentedMovieEditViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    val rentedMovieEditUiState by viewModel.rentedMovieEditUiState.collectAsState()
    var feedbackMessageId by remember {mutableStateOf(R.string.edit_confirmation_message)}
    var isDialogVisible by remember {mutableStateOf(false)}

    Scaffold(
        topBar = {
            VideoLibraryTopAppBar(
                title = stringResource(R.string.edit_rented_movie_bar_title),
                showBackButton = true,
                modifier = modifier,
                onBackClick = onBackClick,
                onMenuItemClick = onMenuItemClick
            )
        }
    ) {
            innerPadding ->
        InputForm(
            rentedMovieUiState = rentedMovieEditUiState,
            buttonText = stringResource(R.string.edit),
            onValueChange = viewModel::updateUiState,
            onButtonClick = {
                coroutineScope.launch {
                    feedbackMessageId = viewModel.updateRentedMovie()
                    isDialogVisible = true
                }
            },
            modifier = Modifier.padding(innerPadding)
        )

        if(isDialogVisible) {
            AlertMessageBox(
                messageId = feedbackMessageId,
                onDismiss = {
                    isDialogVisible = false
                    if(feedbackMessageId == R.string.edit_confirmation_message) {
                        onBackClick()
                    }
                }
            )
        }
    }
}

//@Composable
//@Preview(showBackground = true)
//fun RentedMovieEditScreenPreview() {
//    VideoLibraryTheme {
//        RentedMovieEditScreen(
//            onBackClick = {}
//        )
//    }
//}