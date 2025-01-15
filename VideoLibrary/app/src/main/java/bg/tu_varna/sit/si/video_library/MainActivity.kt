package bg.tu_varna.sit.si.video_library

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import bg.tu_varna.sit.si.video_library.ui.VideoLibraryNavHost
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
    val navController = rememberNavController()
    VideoLibraryNavHost(navController = navController)
}
