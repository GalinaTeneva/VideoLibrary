package bg.tu_varna.sit.si.video_library.ui.customer.state

import bg.tu_varna.sit.si.video_library.data.entities.Customer

data class CustomerHomeUiState(
    val customersList: List<Customer> = listOf(),
    val isLoading: Boolean = false
)