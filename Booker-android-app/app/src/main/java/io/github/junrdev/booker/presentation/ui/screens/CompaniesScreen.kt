package io.github.junrdev.booker.presentation.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import io.github.junrdev.booker.databinding.FragmentCompaniesScreenBinding
import io.github.junrdev.booker.presentation.adapter.CompanyListAdapter
import io.github.junrdev.booker.presentation.viewmodel.CompaniesViewModel
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CompaniesScreen : Fragment() {
    lateinit var binding: FragmentCompaniesScreenBinding
    private val companiesViewModel by viewModels<CompaniesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentCompaniesScreenBinding.inflate(inflater).also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            companiesViewModel.allCompaniesUiState.collect { state ->

                if (state.isLoading) {
                    Toast.makeText(requireContext(), "Please wait", Toast.LENGTH_SHORT).show()
                }

                if (state.data != null) {
                    binding.companyList.adapter = CompanyListAdapter(state.data)
                }

                if (state.error != null) {
                    Toast.makeText(requireContext(), "Failed due to ${state.error}", Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}