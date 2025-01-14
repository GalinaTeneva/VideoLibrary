package bg.tu_varna.sit.si.video_library.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customers")
data class Customer(
    @PrimaryKey(autoGenerate = true)
    val customerId: Int = 0,
    val personalId: String,
    val name: String,
    val address: String
)
