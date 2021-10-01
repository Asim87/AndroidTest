package com.example.testgradle.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testgradle.R
import com.example.testgradle.interfaceUtil.EmployeeListingInterface
import com.example.testgradle.models.Employee

class EmployeeAdapter(private var employeeList: ArrayList<Employee>,  val employeeInterface: EmployeeListingInterface) : RecyclerView.Adapter<EmployeeAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.employee_item, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(employeeList[position], employeeInterface)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return employeeList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(employee: Employee, employeeCallback: EmployeeListingInterface) {
            val textViewName = itemView.findViewById(R.id.employeeName) as TextView
            val textViewAddress  = itemView.findViewById(R.id.employeeAge) as TextView
            val btnEdit = itemView.findViewById(R.id.editBtn) as Button
            val btnDelete = itemView.findViewById(R.id.deleteBtn) as Button
            textViewName.text = employee.name
            textViewAddress.text = employee.age

            btnEdit.setOnClickListener { employeeCallback.edit(employee) }
            btnDelete.setOnClickListener { employeeCallback.delete(employee) }
            itemView.setOnClickListener { employeeCallback.view(employee) }
        }
    }


    fun addData(employees: List<Employee>) {
        employeeList = ArrayList()
        employeeList.addAll(employees)
    }
}