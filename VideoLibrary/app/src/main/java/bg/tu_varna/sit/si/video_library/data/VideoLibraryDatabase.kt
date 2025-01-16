package bg.tu_varna.sit.si.video_library.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import bg.tu_varna.sit.si.video_library.data.dao.CustomerDao
import bg.tu_varna.sit.si.video_library.data.dao.MovieDao
import bg.tu_varna.sit.si.video_library.data.dao.RentedMovieDao
import bg.tu_varna.sit.si.video_library.data.entities.Customer
import bg.tu_varna.sit.si.video_library.data.entities.Movie
import bg.tu_varna.sit.si.video_library.data.entities.RentedMovie

@Database(
    entities = [Movie::class, Customer::class, RentedMovie::class],
    version = 1,
    exportSchema = false
)
abstract class VideoLibraryDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun customerDao(): CustomerDao
    abstract fun rentedMovieDao(): RentedMovieDao

    companion object {
        @Volatile
        private var Instance: VideoLibraryDatabase? = null

        fun getDatabase(context: Context): VideoLibraryDatabase {

            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    VideoLibraryDatabase::class.java,
                    "video_library_db"
                )
                    .build().also { Instance = it}
            }
        }
    }
}