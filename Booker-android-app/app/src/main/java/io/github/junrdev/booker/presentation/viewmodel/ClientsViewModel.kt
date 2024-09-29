package io.github.junrdev.booker.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.junrdev.booker.data.util.ResponseWrapper
import io.github.junrdev.booker.domain.use_cases.ClientsUseCase
import io.github.junrdev.booker.presentation.ui.util.UiStateWrapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import src.main.graphql.GetClientByIdQuery
import src.main.graphql.GetClientsQuery
import javax.inject.Inject

@HiltViewModel
class ClientsViewModel @Inject constructor(
    private val clientsUseCase: ClientsUseCase
) : ViewModel() {


    private val _clientsUiState = MutableStateFlow(UiStateWrapper<GetClientsQuery.Data>())
    val clientsUiState: StateFlow<UiStateWrapper<GetClientsQuery.Data>>
        get() = _clientsUiState.asStateFlow()

    private val _clientUiState = MutableStateFlow(UiStateWrapper<GetClientByIdQuery.Data>())
    val clientUiState: StateFlow<UiStateWrapper<GetClientByIdQuery.Data>>
        get() = _clientUiState.asStateFlow()

    init {
        getClients()
    }

    private fun getClients() = clientsUseCase.getAllClientsCase()
        .onEach { emission ->
            when (emission) {
                is ResponseWrapper.Error -> _clientsUiState.update {
                    it.copy(error = emission.message)
                }

                is ResponseWrapper.Loading -> _clientsUiState.update {
                    it.copy(isLoading = true)
                }

                is ResponseWrapper.Success -> _clientsUiState.update {
                    it.copy(data = emission.data)
                }
            }
        }
        .launchIn(viewModelScope)

    fun getClientById(id:String) = clientsUseCase.getClientByIdCase(id)
        .onEach { emission ->
            when (emission) {
                is ResponseWrapper.Error -> _clientUiState.update {
                    it.copy(error = emission.message)
                }

                is ResponseWrapper.Loading -> _clientUiState.update {
                    it.copy(isLoading = true)
                }

                is ResponseWrapper.Success -> _clientUiState.update {
                    it.copy(data = emission.data)
                }
            }
        }
        .launchIn(viewModelScope)
}