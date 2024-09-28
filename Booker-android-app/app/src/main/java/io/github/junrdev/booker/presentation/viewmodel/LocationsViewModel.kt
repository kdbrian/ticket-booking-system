package io.github.junrdev.booker.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.junrdev.booker.data.util.ResponseWrapper
import io.github.junrdev.booker.domain.use_cases.LocationsUseCase
import io.github.junrdev.booker.presentation.ui.util.UiStateWrapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import src.main.graphql.FetchCountiesQuery
import src.main.graphql.FetchSubCountiesQuery
import src.main.graphql.FetchSubCountyByCountyNameQuery
import src.main.graphql.FetchSubCountyByCountyNumberQuery
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val locationsUseCase: LocationsUseCase
) : ViewModel() {

    private val _countiesUiState = MutableStateFlow(UiStateWrapper<FetchCountiesQuery.Data>())
    val countiesUiState: StateFlow<UiStateWrapper<FetchCountiesQuery.Data>> =
        _countiesUiState.asStateFlow()

    private val _subcountiesUiState = MutableStateFlow(UiStateWrapper<FetchSubCountiesQuery.Data>())
    val subcountiesUiState: StateFlow<UiStateWrapper<FetchSubCountiesQuery.Data>> =
        _subcountiesUiState.asStateFlow()


    private val _subcountiesByNameUiState =
        MutableStateFlow(UiStateWrapper<FetchSubCountyByCountyNameQuery.Data>())
    val subcountiesByNameUiState: StateFlow<UiStateWrapper<FetchSubCountyByCountyNameQuery.Data>> =
        _subcountiesByNameUiState.asStateFlow()


    private val _subcountiesByCountyIdUiState =
        MutableStateFlow(UiStateWrapper<FetchSubCountyByCountyNumberQuery.Data>())
    val subcountiesByCountyIdUiState: StateFlow<UiStateWrapper<FetchSubCountyByCountyNumberQuery.Data>> =
        _subcountiesByCountyIdUiState.asStateFlow()

    init {
        getCounties()
        getSubCounties()
    }

    private fun getCounties() = locationsUseCase.getCounties()
        .onEach { emission ->
            when (emission) {
                is ResponseWrapper.Error -> _countiesUiState.update {
                    it.copy(error = emission.message)
                }

                is ResponseWrapper.Loading -> _countiesUiState.update {
                    it.copy(isLoading = true)
                }

                is ResponseWrapper.Success -> _countiesUiState.update {
                    it.copy(data = emission.data)
                }
            }

        }
        .launchIn(viewModelScope)

    private fun getSubCounties() = locationsUseCase.getSubCounties()
        .onEach { emission ->

            when (emission) {
                is ResponseWrapper.Error -> _subcountiesUiState.update {
                    it.copy(error = emission.message)
                }

                is ResponseWrapper.Loading -> _subcountiesUiState.update {
                    it.copy(isLoading = true)
                }

                is ResponseWrapper.Success -> _subcountiesUiState.update {
                    it.copy(data = emission.data)
                }
            }

        }
        .launchIn(viewModelScope)

    fun getSubCountyByCountyName(name: String) =
        locationsUseCase.getSubCountiesByCountyName("$name ")
            .onEach { emission ->

                when (emission) {
                    is ResponseWrapper.Error -> _subcountiesByNameUiState.update {
                        it.copy(error = emission.message)
                    }

                    is ResponseWrapper.Loading -> _subcountiesByNameUiState.update {
                        it.copy(isLoading = true)
                    }

                    is ResponseWrapper.Success -> _subcountiesByNameUiState.update {
                        it.copy(data = emission.data)
                    }
                }

            }
            .launchIn(viewModelScope)

    fun getSubCountyByCountyNumber(num: Int) =
        locationsUseCase.getSubCountiesByCountyNumber(num)
            .onEach { emission ->

                when (emission) {
                    is ResponseWrapper.Error -> _subcountiesByCountyIdUiState.update {
                        it.copy(error = emission.message)
                    }

                    is ResponseWrapper.Loading -> _subcountiesByCountyIdUiState.update {
                        it.copy(isLoading = true)
                    }

                    is ResponseWrapper.Success -> _subcountiesByCountyIdUiState.update {
                        it.copy(data = emission.data)
                    }
                }

            }
            .launchIn(viewModelScope)
}

