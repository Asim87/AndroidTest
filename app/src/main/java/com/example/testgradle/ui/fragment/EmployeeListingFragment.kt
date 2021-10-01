package com.example.testgradle.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testgradle.R
import com.example.testgradle.interfaceUtil.EmployeeListingInterface
import com.example.testgradle.adapters.EmployeeAdapter
import com.example.testgradle.databinding.FragmentEmployeeListingBinding
import com.example.testgradle.models.Employee
import com.example.testgradle.viewModel.EmployeeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EmployeeListingFragment : Fragment(), EmployeeListingInterface {

    private lateinit var adapter: EmployeeAdapter
    val employeeViewModel: EmployeeViewModel by activityViewModels()
    lateinit var binding: FragmentEmployeeListingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding  = FragmentEmployeeListingBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        observerUsers()
        binding.btnAdd.setOnClickListener {
            employeeViewModel.setEmployee(null)
            findNavController().navigate(R.id.fr_add)

        }
    }


    private fun observerUsers() {

        employeeViewModel.getEmployeeList.observe(viewLifecycleOwner,{emplist ->
            emplist.let {
                renderList(it)
            }
        })
    }
    private fun setRecyclerView() {
        binding.rvEmployees.layoutManager = LinearLayoutManager(requireContext())
        adapter = EmployeeAdapter(ArrayList(),this)
        binding.rvEmployees.adapter = adapter
        employeeViewModel.getDataFromServer()
    }
    private fun renderList(users: List<Employee>) {
        adapter.apply {
            addData(users)
            notifyDataSetChanged()
        }
    }

    override fun edit(employee: Employee) {
//        val action =
        employeeViewModel.setEmployee(employee)
        findNavController().navigate(R.id.fr_add)
    }

    override fun view(employee: Employee) {
        employeeViewModel.setEmployee(employee)
        findNavController().navigate(R.id.fr_view_employee)

    }

    override fun delete(employee: Employee) {
        employeeViewModel.delete(employee)
    }
}