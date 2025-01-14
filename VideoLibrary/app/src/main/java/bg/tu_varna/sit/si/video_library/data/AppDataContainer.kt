package bg.tu_varna.sit.si.video_library.data

import android.content.Context
import bg.tu_varna.sit.si.video_library.data.repositories.CustomerRepository
import bg.tu_varna.sit.si.video_library.data.repositories.CustomerRepositoryImp
import bg.tu_varna.sit.si.video_library.data.repositories.MovieRepository
import bg.tu_varna.sit.si.video_library.data.repositories.MovieRepositoryImp

class AppDataContainer(private val context: Context) {
    val movieRepository: MovieRepository by lazy {
        MovieRepositoryImp(VideoLibraryDatabase.getDatabase(context).movieDao())
    }
    val customerRepository: CustomerRepository by lazy {
        CustomerRepositoryImp(VideoLibraryDatabase.getDatabase(context).customerDao())
    }
}