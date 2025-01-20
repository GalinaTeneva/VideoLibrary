package bg.tu_varna.sit.si.video_library.ui.rented_movies

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
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
fun RentedMovieEditScreen(
    onBackClick: () -> Unit,
    onMenuItemClick:(Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RentedMovieEditViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val rentedMovieEditUiState by viewModel.rentedMovieEditUiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

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
                    viewModel.updateRentedMovie()
                    onBackClick()
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
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