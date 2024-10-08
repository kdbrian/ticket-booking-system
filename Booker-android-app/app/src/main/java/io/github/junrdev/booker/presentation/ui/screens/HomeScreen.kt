package io.github.junrdev.booker.presentation.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.junrdev.booker.R
import io.github.junrdev.booker.databinding.FragmentHomeScreenBinding
import io.github.junrdev.booker.presentation.adapter.CompanyListAdapter
import io.github.junrdev.booker.presentation.adapter.RouteListAdapter
import io.github.junrdev.booker.presentation.viewmodel.ClientsViewModel
import io.github.junrdev.booker.presentation.viewmodel.CompaniesViewModel
import io.github.junrdev.booker.presentation.viewmodel.LocationsViewModel
import io.github.junrdev.booker.presentation.viewmodel.RoutesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import src.main.graphql.type.County
import src.main.graphql.type.SubCounty
import java.text.SimpleDateFormat
import java.util.Date

@AndroidEntryPoint
class HomeScreen : Fragment() {

    lateinit var binding: FragmentHomeScreenBinding
    private val companiesViewModel by viewModels<CompaniesViewModel>()
    private val routesViewModel by viewModels<RoutesViewModel>()
    private val locationsViewModel by viewModels<LocationsViewModel>()
    private val clientsViewModel by viewModels<ClientsViewModel>()


    private lateinit var selectedDate: Date
    var county: County? = null
    var subCounty: SubCounty? = null

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

            val fromSubCountyClickListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    p1: View?,
                    p2: Int,
                    p3: Long
                ) {
                    val sub = p0?.getItemAtPosition(p2).toString()
                    val subCountySelect = locationsViewModel.subcountiesByNameUiState.value.data?.getSubCountiesByCountyName?.firstOrNull { it.subCountyName == sub }
                    println(
                        "sub $sub\n"+
                                "subse $subCountySelect"
                    )
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }


            CoroutineScope(Dispatchers.Main).launch {
                routesViewModel.routesUiState.collect { state ->
                    if (state.isLoading) {
                    }

                    if (state.data != null) {
                        routeList.adapter = RouteListAdapter(state.data){
                            findNavController()
                                .navigate(R.id.action_homeScreen_to_viewBookTicket, bundleOf("route" to it))
                        }
                    }

                    if (state.error != null) {
                        Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
                    }

                }
            }


            CoroutineScope(Dispatchers.Main).launch {
                locationsViewModel.countiesUiState.collect { state ->
                    if (state.isLoading) {
                    }

                    if (state.data != null) {
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
                        Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }


            CoroutineScope(Dispatchers.Main).launch {
                locationsViewModel.subcountiesByNameUiState.collect { state ->
                    if (state.isLoading) {
                    }

                    if (state.data != null) {
                        spinner.adapter = ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_spinner_item,
                            state.data.getSubCountiesByCountyName!!.map { it.subCountyName })

                        spinner.onItemSelectedListener = fromSubCountyClickListener
                        spinner2.adapter = ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_spinner_item,
                            state.data.getSubCountiesByCountyName.map { it.subCountyName })


                    }

                    if (state.error != null) {
                        Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }

            CoroutineScope(Dispatchers.Main).launch {
                companiesViewModel.allCompaniesUiState.collect { state ->
                    if (state.isLoading) {
                    }

                    if (state.data != null) {
                        availableCompanies.adapter = CompanyListAdapter(state.data)
                    }

                    if (state.error != null) {
                        Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
                    }

                }
            }


            CoroutineScope(Dispatchers.Main).launch {
                clientsViewModel.getClientById("66f89f805497c47c1fcb7660")
                    .invokeOnCompletion {
                        println("Got client")
                        println(it)
                    }
                clientsViewModel.clientUiState.collect { state ->

                    if (state.isLoading) {
                        textView7.text = "Please wait"
                        textView8.text = "fetching account ...."
                    }


                    if (state.data != null) {
                        delay(800L)
                        val user = state.data.getClientById
                        textView7.text = user.fullName
                        textView8.text = user.identification
                    }

                    if (state.error != null) {
                        textView8.apply {
                            text = "failed retry later.."
                            setTextColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.light_blue_900
                                )
                            )
                        }

                    }

                }
            }

            switch1.setOnCheckedChangeListener { _, isChecked ->
                editTextDate2.isEnabled = isChecked
            }

            editTextDate2.setOnClickListener {
                findNavController().navigate(R.id.action_homeScreen_to_datePickerDialog)
            }

            setFragmentResultListener("date") { _, bundle ->
                val pickedDate = bundle.getString("date")
                val date = Date(Date.parse(pickedDate))
                editTextDate2.apply {
                    setText(SimpleDateFormat.getInstance().format(date))
                    switch1.isChecked = true
                }
            }

            textView14.setOnClickListener {
                findNavController().navigate(R.id.action_homeScreen_to_companiesScreen)
            }


            button.setOnClickListener {
                findNavController().navigate(R.id.action_homeScreen_to_searchResults)
            }


            imageView3.setOnClickListener {
                findNavController().navigate(R.id.action_homeScreen_to_routeScreen)
            }


        }
    }

}