package com.example.testgradle.viewModel

import androidx.lifecycle.*
import com.example.testgradle.models.Employee
import com.example.testgradle.repository.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EmployeeViewModel @Inject constructor(private val employeeRepository: EmployeeRepository) : ViewModel() {

    var employee : MutableLiveData<Employee> = MutableLiveData()
    val selectedItem: LiveData<Employee> get() = employee



        val getEmployeeList: LiveData<List<Employee>>
        get() =
            employeeRepository.getEmployeeList.flowOn(Dispatchers.Main)
            .asLiveData(context = viewModelScope.coroutineContext)
//  val getEmployeeList: StateFlow<List<Employee>?>
//        get() =
//                employeeRepository.getEmployeeList.stateIn(viewModelScope, SharingStarted.Lazily, ArrayList())

    fun insert(employee:Employee){
        viewModelScope.launch {
            employeeRepository.insert(employee)
        }
    }

    fun getDataFromServer(){
        viewModelScope.launch {
            employeeRepository.getEmployeesFromServer()
        }
    }

    fun update(employee:Employee){
        viewModelScope.launch {
            employeeRepository.update(employee)
        }
    }

    fun delete(employee: Employee){
        viewModelScope.launch {
            employeeRepository.delete(employee)

        }
        getEmployeeList
    }

    fun setEmployee(data: Employee?) {
        employee.value = data
    }


//    fun getEmployeeInfo() : MutableLiveData<Employee> {
//        return employee
//    }
}