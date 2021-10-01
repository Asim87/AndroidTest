package com.example.testgradle.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testgradle.models.Employee


@Database(
    entities = [Employee::class],
    version = 1
)
abstract class EmployeeDb : RoomDatabase() {
    abstract fun getEmployeeDao(): EmployeeDao
}