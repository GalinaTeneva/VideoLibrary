package bg.tu_varna.sit.si.video_library.ui.customer.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bg.tu_varna.sit.si.video_library.data.repositories.CustomerRepository
import bg.tu_varna.sit.si.video_library.ui.customer.state.CustomerHomeUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class CustomersHomeViewModel(customerRepository: CustomerRepository) : ViewModel() {
    val customerHomeUiState: StateFlow<CustomerHomeUiState> =
        customerRepository.getAllCustomersStream()
            .map { customers -> CustomerHomeUiState(customersList = customers, isLoading = false) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = CustomerHomeUiState(isLoading = true)
            )
}

