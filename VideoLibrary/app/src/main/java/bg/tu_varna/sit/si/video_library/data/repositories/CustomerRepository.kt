package bg.tu_varna.sit.si.video_library.data.repositories

import bg.tu_varna.sit.si.video_library.data.entities.Customer
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {
    fun getAllCustomersStream(): Flow<List<Customer>>
    fun getCustomerStream(id: Int): Flow<Customer>

    suspend fun isCustomerExists(id: Int): Boolean
    suspend fun insertCustomer(customer: Customer)
    suspend fun updateCustomer(customer: Customer)
    suspend fun deleteCustomer(customer: Customer)
}