package bg.tu_varna.sit.si.video_library

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import bg.tu_varna.sit.si.video_library.ui.theme.VideoLibraryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VideoLibraryTheme {
                Application()
            }
        }
    }
}

@Composable
fun Application() {
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    VideoLibraryTheme {
//        Greeting("Android")
//    }
//}