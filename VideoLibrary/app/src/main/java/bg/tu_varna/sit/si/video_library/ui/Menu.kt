package bg.tu_varna.sit.si.video_library.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import bg.tu_varna.sit.si.video_library.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoLibraryTopAppBar(
    title: String,
    showBackButton: Boolean,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onMenuItemClick: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    CenterAlignedTopAppBar(
        title = {Text(title)},
        modifier = modifier,
        actions = {
            if(showBackButton) {
                IconButton(onClick = {expanded = !expanded}) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = stringResource(R.string.menu)
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {expanded = false}
                ) {
                    DropdownMenuItem(
                        text = { Text(text = stringResource(R.string.movies)) },
                        onClick = {
                            expanded = false
                            onMenuItemClick(R.string.movies)
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = stringResource(R.string.customers)) },
                        onClick = {
                            expanded = false
                            onMenuItemClick(R.string.customers)
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = stringResource(R.string.rented_movies)) },
                        onClick = {
                            expanded = false
                            onMenuItemClick(R.string.rented_movies)
                        }
                    )
                }
            }
        },
        navigationIcon = {
            if(showBackButton) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            }
        }
    )
}