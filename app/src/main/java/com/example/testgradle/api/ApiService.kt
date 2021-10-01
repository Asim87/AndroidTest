package com.example.testgradle.api

import com.example.testgradle.models.Employee
import retrofit2.http.*

interface ApiService {

    @GET("employees")
    suspend fun getEmployees(): List<Employee>

    @PUT("employees/{empId}")
    suspend fun updateEmployee(@Body employee:Employee, @Path(value = "empId") empId : Int): Employee

    @POST("employees")
    suspend fun addEmployee(@Body employee:Employee): Employee

    @DELETE("employees/{empId}")
    suspend fun deleteEmployee(@Path(value = "empId") empId: Int): Employee

}