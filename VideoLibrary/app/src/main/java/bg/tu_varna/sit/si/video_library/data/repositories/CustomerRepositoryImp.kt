package bg.tu_varna.sit.si.video_library.data.repositories

import bg.tu_varna.sit.si.video_library.data.dao.CustomerDao
import bg.tu_varna.sit.si.video_library.data.entities.Customer
import kotlinx.coroutines.flow.Flow

class CustomerRepositoryImp(private val customerDao: CustomerDao) : CustomerRepository {
    override fun getAllCustomersStream(): Flow<List<Customer>> = customerDao.getAllCustomers()

    override fun getCustomerStream(id: Int): Flow<Customer> = customerDao.getCustomer(id)

    override suspend fun insertCustomer(customer: Customer) = customerDao.insert(customer)

    override suspend fun updateCustomer(customer: Customer) = customerDao.update(customer)

    override suspend fun deleteCustomer(customer: Customer) = customerDao.delete(customer)

}