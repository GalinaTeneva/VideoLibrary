package bg.tu_varna.sit.si.video_library

import android.app.Application
import bg.tu_varna.sit.si.video_library.data.AppDataContainer
import bg.tu_varna.sit.si.video_library.data.DatabaseInitializer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VideoLibraryApplication : Application() {
    lateinit var container: AppDataContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)

        // Initialize the database with default data
        CoroutineScope(Dispatchers.IO).launch {
            DatabaseInitializer.initializeData(container)
        }
    }
}