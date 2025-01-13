package bg.tu_varna.sit.si.video_library.data.entities

data class Customer(
    val customerId: Int = 0,
    val personalId: String,
    val name: String,
    val address: String
)
