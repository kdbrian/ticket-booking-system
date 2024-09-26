package io.github.junrdev.booker.presentation.companies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.junrdev.booker.util.ResponseWrapper
import io.github.junrdev.booker.domain.Provider
import io.github.junrdev.booker.domain.use_cases.CompaniesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import src.main.graphql.FetchCompaniesQuery

class CompaniesViewModel(
    private val companiesUseCase: CompaniesUseCase = Provider.provideCompaniesUseCase()
) : ViewModel() {

    private val _allCompaniesUiState =
        MutableStateFlow(CompaniesScreenState.UiState1<FetchCompaniesQuery.Data>())
    val allCompaniesUiState: StateFlow<CompaniesScreenState.UiState1<FetchCompaniesQuery.Data>> =
        _allCompaniesUiState.asStateFlow()


    init {
        getCompanies()
    }

    private fun getCompanies() = companiesUseCase.getCompanies()
        .onEach { emition ->
            when (emition) {
                is ResponseWrapper.Error -> _allCompaniesUiState.update {
                    CompaniesScreenState.UiState1(
                        error = emition.message.toString()
                    )
                }

                is ResponseWrapper.Loading -> _allCompaniesUiState.update {
                    CompaniesScreenState.UiState1(
                        isLoading = true
                    )
                }

                is ResponseWrapper.Success -> _allCompaniesUiState.update {
                    CompaniesScreenState.UiState1(
                        data = emition.data
                    )
                }
            }
        }.launchIn(viewModelScope)
}

object CompaniesScreenState {

    data class UiState1<T>(
        val isLoading: Boolean = false,
        val error: String = "",
        val data: T? = null
    )

    data class UiState(
        val isLoading: Boolean = false,
        val error: String = "",
        val data: FetchCompaniesQuery.Data? = null
    )

}