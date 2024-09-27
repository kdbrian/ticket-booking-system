package io.github.junrdev.booker.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.junrdev.booker.domain.use_cases.CompaniesUseCase
import io.github.junrdev.booker.data.util.ResponseWrapper
import io.github.junrdev.booker.presentation.ui.util.UiStateWrapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import src.main.graphql.FetchCompaniesQuery
import javax.inject.Inject

@HiltViewModel
class CompaniesViewModel @Inject constructor(
    private val companiesUseCase: CompaniesUseCase
) : ViewModel() {

    private val _allCompaniesUiState =
        MutableStateFlow(UiStateWrapper<FetchCompaniesQuery.Data>())
    val allCompaniesUiState: StateFlow<UiStateWrapper<FetchCompaniesQuery.Data>> =
        _allCompaniesUiState.asStateFlow()


    init {
        getCompanies()
    }

    private fun getCompanies() = companiesUseCase.getCompanies()
        .onEach { emition ->
            when (emition) {
                is ResponseWrapper.Error -> _allCompaniesUiState.update {
                    UiStateWrapper(
                        error = emition.message.toString()
                    )
                }

                is ResponseWrapper.Loading -> _allCompaniesUiState.update {
                    UiStateWrapper(
                        isLoading = true
                    )
                }

                is ResponseWrapper.Success -> _allCompaniesUiState.update {
                    UiStateWrapper(
                        data = emition.data
                    )
                }
            }
        }.launchIn(viewModelScope)
}

object CompaniesScreenState {

}