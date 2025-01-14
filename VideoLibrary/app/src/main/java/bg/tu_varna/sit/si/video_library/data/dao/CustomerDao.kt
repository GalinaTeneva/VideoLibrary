package bg.tu_varna.sit.si.video_library.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import bg.tu_varna.sit.si.video_library.data.entities.Customer
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(customer: Customer)

    @Update
    suspend fun update(customer: Customer)

    @Delete
    suspend fun delete(customer: Customer)

    @Query(
        """
            SELECT *
            FROM customers
            WHERE customerId = :id
        """
    )
    fun getCustomer(id: Int): Flow<Customer>

    @Query(
        """
            SELECT *
            FROM customers
            ORDER BY name ASC
        """
    )
    fun getAllCustomers(): Flow<List<Customer>>
}