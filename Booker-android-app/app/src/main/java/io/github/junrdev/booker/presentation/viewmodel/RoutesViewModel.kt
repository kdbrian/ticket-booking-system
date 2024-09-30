package io.github.junrdev.booker.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.junrdev.booker.data.util.ResponseWrapper
import io.github.junrdev.booker.domain.use_cases.RoutesUseCase
import io.github.junrdev.booker.presentation.ui.util.UiStateWrapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import src.main.graphql.GetAllRoutesQuery
import src.main.graphql.GetRouteByIdQuery
import javax.inject.Inject

@HiltViewModel
class RoutesViewModel @Inject constructor(
    private val routesUseCase: RoutesUseCase
) : ViewModel() {

    private val _routesUiState = MutableStateFlow(UiStateWrapper<GetAllRoutesQuery.Data>())
    val routesUiState: StateFlow<UiStateWrapper<GetAllRoutesQuery.Data>> =
        _routesUiState.asStateFlow()

    private val _routeByIdUiState = MutableStateFlow(UiStateWrapper<GetRouteByIdQuery.Data>())
    val routesByIdUiState: StateFlow<UiStateWrapper<GetRouteByIdQuery.Data>> =
        _routeByIdUiState.asStateFlow()

    init {
        getRoutes()
    }


    private fun getRoutes() = routesUseCase.getAllRoutes().onEach { emission ->

        when(emission){
            is ResponseWrapper.Error -> _routesUiState.update { UiStateWrapper(error = emission.message.toString()) }
            is ResponseWrapper.Loading -> _routesUiState.update { UiStateWrapper(isLoading = true) }
            is ResponseWrapper.Success -> _routesUiState.update { UiStateWrapper(data = emission.data) }
        }
    }.launchIn(viewModelScope)

    fun getRouteById(id : String)=
        routesUseCase.getRouteById(id).onEach {emission ->
            when(emission){
                is ResponseWrapper.Error -> _routeByIdUiState.update { UiStateWrapper(error = emission.message.toString()) }
                is ResponseWrapper.Loading -> _routeByIdUiState.update { UiStateWrapper(isLoading = true) }
                is ResponseWrapper.Success -> _routeByIdUiState.update { UiStateWrapper(data = emission.data) }
            }
        }.launchIn(viewModelScope)


}

