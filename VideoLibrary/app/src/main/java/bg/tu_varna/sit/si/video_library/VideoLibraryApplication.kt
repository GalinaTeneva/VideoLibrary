package bg.tu_varna.sit.si.video_library

import android.app.Application
import bg.tu_varna.sit.si.video_library.data.AppDataContainer

class VideoLibraryApplication : Application() {
    lateinit var container: AppDataContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}