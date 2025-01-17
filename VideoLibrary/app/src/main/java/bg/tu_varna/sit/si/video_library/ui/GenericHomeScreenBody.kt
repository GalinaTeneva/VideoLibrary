package bg.tu_varna.sit.si.video_library.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun <T> GenericHomeScreenBody(
    itemList: List<T>,
    emptyMessage: String,
    modifier: Modifier = Modifier,
    itemListContent: @Composable (List<T>) -> Unit
) {
    if (itemList.isEmpty()) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier.fillMaxSize()
        ) {
            Text(
                text = emptyMessage,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    } else {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            itemListContent(itemList)
        }
    }
}