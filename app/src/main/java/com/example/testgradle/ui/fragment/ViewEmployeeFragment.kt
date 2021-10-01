package com.example.testgradle.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.testgradle.R
import com.example.testgradle.models.Employee
import com.example.testgradle.viewModel.EmployeeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewEmployeeFragment : Fragment() {

    val employeeViewModel: EmployeeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_employee, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEmployee(view)

    }

    private fun observeEmployee(view: View) {
        employeeViewModel.selectedItem.observe(viewLifecycleOwner, {
            if (it == null)
                return@observe
            setUi(it,view)
        })
    }

    private fun setUi(employee:Employee, view: View) {
        view.findViewById<TextView>(R.id.tv_name).text = employee.name
        view.findViewById<TextView>(R.id.tv_email).text = employee.email
        view.findViewById<TextView>(R.id.tv_age).text = employee.age
        view.findViewById<TextView>(R.id.tv_date).text = employee.dateOfEmployment
        view.findViewById<TextView>(R.id.tv_salary).text = employee.salary
        view.findViewById<TextView>(R.id.tv_assignment).text = employee.assignment
        view.findViewById<TextView>(R.id.tv_department).text = employee.department
        view.findViewById<TextView>(R.id.tv_address).text = employee.address
    }

}