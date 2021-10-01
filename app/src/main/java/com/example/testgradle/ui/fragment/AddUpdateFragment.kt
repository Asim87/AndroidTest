package com.example.testgradle.ui.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.testgradle.databinding.FragmentAddUpdateBinding
import com.example.testgradle.models.Employee
import com.example.testgradle.viewModel.EmployeeViewModel
import dagger.hilt.android.AndroidEntryPoint
import android.widget.ArrayAdapter
import com.example.testgradle.util.EnumEmployeeAssignment
import com.example.testgradle.util.Utility
import java.util.*


@AndroidEntryPoint
class AddUpdateFragment : Fragment() {


    lateinit var binding: FragmentAddUpdateBinding
    val employeeViewModel: EmployeeViewModel by activityViewModels()
     var employee: Employee? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      binding =  FragmentAddUpdateBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        initUI()
    }
    private fun observeData() {
        employeeViewModel.selectedItem.observe(requireActivity(),  {
            if (it == null) {
                return@observe
            }
                employee = it
                populateData()
        })
    }

    private fun initUI() {
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            EnumEmployeeAssignment.values()
        )
        binding.etDate.setText(Utility.getCurrentDate())
        binding.etAssignment.setAdapter(adapter)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        binding.etDate.setOnClickListener {

            val dpd = DatePickerDialog(requireContext(), { _, year, _, dayOfMonth ->
                // Display Selected date in TextView
                binding.etDate.setText("$dayOfMonth-${month + 1}-$year")
            }, year, month, day)
            dpd.show()

        }
        binding.etAssignment.setOnClickListener { binding.etAssignment.showDropDown() }
        binding.btnSubmit.setOnClickListener {
            if (!validateData())
                return@setOnClickListener
            var shouldInsert = false
            if (employee == null) {
                employee = Employee()
              shouldInsert = true
            }

                employee = Employee(
                    employee!!.id,
                    binding.etName.text.toString(),
                    binding.etEmail.text.toString(),
                    binding.etPhone.text.toString(),
                    binding.etAge.text.toString(),
                    binding.etDepartment.text.toString(),
                    binding.etAssignment.text.toString(),
                    binding.etSalary.text.toString(),
                    binding.etDate.text.toString(),
                    binding.etAddress.text.toString()
                )
            if (shouldInsert)
                employeeViewModel.insert(employee!!)
            else
                employeeViewModel.update(employee!!)


            requireActivity().onBackPressed();
        }
    }
    private fun populateData(){
        binding.etName.setText(employee!!.name)
        binding.etEmail.setText(employee!!.email)
        binding.etAge.setText(employee!!.age)
        binding.etDepartment.setText(employee!!.department)
        binding.etSalary.setText(employee!!.salary)
        binding.etAssignment.setText(employee!!.assignment)
        binding.etDate.setText(employee!!.dateOfEmployment)
        binding.etAddress.setText(employee!!.address)
        binding.etPhone.setText(employee!!.phone)
    }

    private fun validateData() :Boolean {
        val message = "should not be empty"
        if (binding.etName.text.toString().isEmpty()) {
            Utility.showToast("Name $message",requireContext())
            return false
        }
         if (binding.etEmail.text.toString().isEmpty()) {
            Utility.showToast("Email $message",requireContext())
            return false
        }
        if (!Utility.isValidEmail(binding.etEmail.text.toString())) {
            Utility.showToast("Enter a valid email address",requireContext())
            return false
        }
         if (binding.etPhone.text.toString().isEmpty()) {
            Utility.showToast("Phone $message",requireContext())
            return false
        }
         if (binding.etDepartment.text.toString().isEmpty()) {
            Utility.showToast("Department $message",requireContext())
            return false
        }
         if (binding.etAge.text.toString().isEmpty()) {
            Utility.showToast("Age $message",requireContext())
            return false
        }
         if (binding.etSalary.text.toString().isEmpty()) {
            Utility.showToast("Salary $message",requireContext())
            return false
        }
//         if (binding.etAddress.text.toString().isEmpty()) {
//            Utility.showToast("Address $message",requireContext())
//            return false
//        }

        return true;
    }

}