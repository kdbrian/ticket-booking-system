package io.github.junrdev.booker.presentation.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import io.github.junrdev.booker.R
import io.github.junrdev.booker.databinding.FragmentHomeScreenBinding
import io.github.junrdev.booker.presentation.adapter.CompanyListAdapter
import io.github.junrdev.booker.presentation.adapter.RouteListAdapter
import io.github.junrdev.booker.presentation.viewmodel.CompaniesViewModel
import io.github.junrdev.booker.presentation.viewmodel.LocationsViewModel
import io.github.junrdev.booker.presentation.viewmodel.RoutesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class HomeScreen : Fragment() {

    lateinit var binding: FragmentHomeScreenBinding
    private val companiesViewModel by viewModels<CompaniesViewModel>()
    private val routesViewModel by viewModels<RoutesViewModel>()
    private val locationsViewModel by viewModels<LocationsViewModel>()
    private lateinit var selectedDate: Date

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentHomeScreenBinding.inflate(inflater).also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            CoroutineScope(Dispatchers.Main).launch {
                routesViewModel.routesUiState.collect { state ->
                    if (state.isLoading) {
                        swiperefresh.isRefreshing = true
                    }

                    if (state.data != null) {
                        swiperefresh.isRefreshing = false
                        routeList.adapter = RouteListAdapter(state.data)
                    }

                    if (state.error != null) {
                        swiperefresh.isRefreshing = false
                        Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
                    }

                }
            }


            CoroutineScope(Dispatchers.Main).launch {
                locationsViewModel.countiesUiState.collect { state ->
                    if (state.isLoading) {
                        swiperefresh.isRefreshing = true
                    }

                    if (state.data != null) {
                        swiperefresh.isRefreshing = false
                        val counties = state.data.getCounties!!

                        spinner5.adapter = ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_spinner_item,
                            counties.map { it.countyName }
                        )

                        spinner5.onItemSelectedListener =
                            object : AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    p0: AdapterView<*>?,
                                    p1: View?,
                                    p2: Int,
                                    p3: Long
                                ) {
                                    val str = p0?.getItemAtPosition(p2).toString()
                                    println("You chose $str")
                                    counties.find { it.countyName == str }?.let {
                                        locationsViewModel.getSubCountyByCountyName(it.countyName!!)
                                    }
                                }

                                override fun onNothingSelected(p0: AdapterView<*>?) {
                                }
                            }
                    }

                    if (state.error != null) {
                        swiperefresh.isRefreshing = false
                        Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }


            CoroutineScope(Dispatchers.Main).launch {
                locationsViewModel.subcountiesByNameUiState.collect { state ->
                    if (state.isLoading) {
                        swiperefresh.isRefreshing = true
                    }

                    if (state.data != null) {
                        swiperefresh.isRefreshing = false
                        spinner.adapter = ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_spinner_item,
                            state.data.getSubCountiesByCountyName!!.map { it.subCountyName })
                        spinner2.adapter = ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_spinner_item,
                            state.data.getSubCountiesByCountyName.map { it.subCountyName })
                    }

                    if (state.error != null) {
                        swiperefresh.isRefreshing = false
                        Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }

            CoroutineScope(Dispatchers.Main).launch {
                companiesViewModel.allCompaniesUiState.collect { state ->
                    if (state.isLoading) {
                        swiperefresh.isRefreshing = true
                    }

                    if (state.data != null) {
                        swiperefresh.isRefreshing = false
                        availableCompanies.adapter = CompanyListAdapter(state.data)
                    }

                    if (state.error != null) {
                        swiperefresh.isRefreshing = false
                        Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
                    }

                }
            }

            switch1.setOnCheckedChangeListener { _, isChecked ->
                editTextDate2.isEnabled = isChecked
            }

            editTextDate2.setOnClickListener {
                println("Clicked")
                findNavController().navigate(R.id.action_homeScreen_to_datePickerDialog)
            }

            setFragmentResultListener("date") { _, bundle ->
                val pickedDate = bundle.getString("date")
                val date = Date(Date.parse(pickedDate))
                editTextDate2.setText(SimpleDateFormat.getInstance().format(date))
            }

            textView14.setOnClickListener {
                findNavController().navigate(R.id.action_homeScreen_to_companiesScreen)
            }
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val currentDateInMillis = calendar.timeInMillis

        val constraintsBuilder = CalendarConstraints.Builder()
            .setStart(currentDateInMillis)

        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setCalendarConstraints(constraintsBuilder.build())
            .build()

        datePicker.addOnPositiveButtonClickListener { selection ->
            // selection is the picked date in milliseconds
            val selectedDateInMillis = selection ?: 0L

            // Convert the milliseconds to a readable date format
            val selectedCalendar = Calendar.getInstance()
            selectedCalendar.timeInMillis = selectedDateInMillis
            selectedDate = selectedCalendar.time
            binding.editTextDate2.setText(
                (SimpleDateFormat(
                    "yyyy-MM-dd",
                    Locale.getDefault()
                )).format(selectedDate)
            )
            binding.editTextDate2.clearFocus()

        }
        datePicker.show(parentFragmentManager, datePicker.toString())
    }

}