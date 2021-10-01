package com.example.testgradle.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "employes"
)
data class Employee(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var name: String = "",
    var email: String = "",
    var phone: String = "",
    var age: String = "",
    var department: String = "",
    var assignment: String = "",
    var salary: String = "",
    var dateOfEmployment: String = "",
    var address: String = ""
    )