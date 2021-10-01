package com.example.testgradle.interfaceUtil

import com.example.testgradle.models.Employee

interface EmployeeListingInterface {
     fun edit(employee: Employee)
     fun view(employee: Employee)
     fun delete(employee: Employee)
}