package io.github.junrdev.booker.presentation.companies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import io.github.junrdev.booker.databinding.FragmentCompaniesScreenBinding
import io.github.junrdev.booker.presentation.companies.adapter.CompanyListAdapter
import io.github.junrdev.booker.presentation.companies.viewmodel.CompaniesViewModel
import kotlinx.coroutines.launch

class CompaniesScreen : Fragment() {
    lateinit var binding: FragmentCompaniesScreenBinding
    lateinit var companiesViewModel: CompaniesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentCompaniesScreenBinding.inflate(inflater).also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CompaniesViewModel() as T
            }
        })[CompaniesViewModel::class].also { companiesViewModel = it }

        lifecycleScope.launch {
            companiesViewModel.allCompaniesUiState.collect { state ->

                if (state.isLoading) {
                    Toast.makeText(requireContext(), "Please wait", Toast.LENGTH_SHORT).show()
                }

                if (state.data != null) {
                    binding.companyList.adapter = CompanyListAdapter(state.data.getCompanies!!)
                }

                if (state.error != null) {
                    Toast.makeText(requireContext(), "Failed due to ${state.error}", Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}