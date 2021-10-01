package com.example.testgradle.repository

import android.content.Context
import androidx.annotation.WorkerThread
import com.example.testgradle.api.ApiService
import com.example.testgradle.db.EmployeeDao
import com.example.testgradle.models.Employee
import com.example.testgradle.util.Utility
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EmployeeRepository @Inject constructor (private val employeeDao: EmployeeDao, private val apiService: ApiService,@ApplicationContext val context: Context) {

    val getEmployeeList: Flow<List<Employee>> = employeeDao.getAllEmployees()

    @WorkerThread
    suspend fun insert(employee:Employee) = withContext(Dispatchers.IO){
        employeeDao.insertEmployee(employee)
        if (Utility.isInternetConnected(context)) {
            apiService.addEmployee(employee)
        }
    }
    @WorkerThread
    suspend fun update(employee:Employee) = withContext(Dispatchers.IO){
        employeeDao.update(employee)
        if (Utility.isInternetConnected(context)) {
            updateEmployeeOnServer(employee)
        }
    }
    @WorkerThread
    suspend fun delete(employee:Employee) = withContext(Dispatchers.IO){
        employeeDao.deleteEmployee(employee)
        if (Utility.isInternetConnected(context)) {
            deleteEmployeeFromServer(employee)
        }
    }

    @WorkerThread
    suspend fun insertAll(empList : List<Employee>) {
        employeeDao.insertAllEmployee(empList)
    }


   suspend fun getEmployeesFromServer() {
       if (Utility.isInternetConnected(context)) {
           val emp = apiService.getEmployees()
           insertAll(emp)
       }
     //  print(emp.size)
    }
    suspend fun updateEmployeeOnServer(employee: Employee) {
        apiService.updateEmployee(employee, employee.id!!)
    }
     suspend fun addEmployeeOnServer(employee: Employee) {
        apiService.addEmployee(employee)
    }
    suspend fun deleteEmployeeFromServer(employee: Employee) {
        apiService.deleteEmployee(employee.id!!)
    }

}