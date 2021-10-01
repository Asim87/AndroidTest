package com.example.testgradle.db

import androidx.room.*
import com.example.testgradle.models.Employee
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertEmployee(employee: Employee)

        @Transaction
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertAllEmployee(employee: List<Employee>)

        @Update
        suspend fun update(employee: Employee)


        @Query("SELECT * FROM employes")
        fun getAllEmployees(): Flow<List<Employee>>

        @Delete
        suspend fun deleteEmployee(employee: Employee )

}