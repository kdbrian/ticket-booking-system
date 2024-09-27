package io.github.junrdev.booker.presentation.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.junrdev.booker.R
import io.github.junrdev.booker.databinding.FragmentHomeScreenBinding
import io.github.junrdev.booker.presentation.adapter.CompanyListAdapter
import io.github.junrdev.booker.presentation.adapter.RouteListAdapter
import io.github.junrdev.booker.presentation.viewmodel.CompaniesViewModel
import io.github.junrdev.booker.presentation.viewmodel.RoutesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeScreen : Fragment() {

    lateinit var binding: FragmentHomeScreenBinding
    private val companiesViewModel by viewModels<CompaniesViewModel>()
    private val routesViewModel by viewModels<RoutesViewModel>()

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

            textView14.setOnClickListener {
                findNavController().navigate(R.id.action_homeScreen_to_companiesScreen)
            }
        }
    }
}