package bg.tu_varna.sit.si.video_library.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import bg.tu_varna.sit.si.video_library.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertMessageBox(
    messageId: Int,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val title = when(messageId) {
        R.string.save_confirmation_message,
        R.string.edit_confirmation_message -> ""
        else -> stringResource(R.string.warning)
    }
    BasicAlertDialog(
        onDismissRequest = onDismiss,
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
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.align(Alignment.Start)
            )
            Text(
                text = stringResource(messageId),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            Button(
                onClick = onDismiss,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = stringResource(R.string.ok))
            }
        }
    }
}