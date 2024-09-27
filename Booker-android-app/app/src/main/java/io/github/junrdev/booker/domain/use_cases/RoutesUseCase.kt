package io.github.junrdev.booker.domain.use_cases

import io.github.junrdev.booker.domain.repo.RouteRepo
import io.github.junrdev.booker.data.util.ResponseWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import src.main.graphql.GetAllRoutesQuery
import src.main.graphql.GetRouteByIdQuery
import src.main.graphql.SaveRoutesMutation
import src.main.graphql.type.RouteDto

class RoutesUseCase (
    private val routeRepo: RouteRepo
) {

    fun getAllRoutes(): Flow<ResponseWrapper<GetAllRoutesQuery.Data>> = flow {
        emit(ResponseWrapper.Loading())

        val resp = routeRepo.getAllRoutes()
        if (resp.isSuccess) {
            emit(ResponseWrapper.Success(data = resp.getOrNull()))
        } else
            emit(ResponseWrapper.Error(message = resp.exceptionOrNull()?.message))
    }
        .catch { emit(ResponseWrapper.Error(message = it.message)) }
        .flowOn(Dispatchers.IO)

    fun getRouteById(id: String): Flow<ResponseWrapper<GetRouteByIdQuery.Data>> = flow {

        emit(ResponseWrapper.Loading())
        val resp = routeRepo.getRouteById(id)

        if (resp.isSuccess) {
            emit(ResponseWrapper.Success(resp.getOrNull()))
        } else
            emit(ResponseWrapper.Error(message = resp.exceptionOrNull()?.message))
    }.catch { emit(ResponseWrapper.Error(message = it.message)) }
        .flowOn(Dispatchers.IO)

    fun addRoute(dto: RouteDto): Flow<ResponseWrapper<SaveRoutesMutation.Data>> = flow {
        emit(ResponseWrapper.Loading())

        val resp = routeRepo.addRoute(dto)
        if (resp.isSuccess) {
            emit(ResponseWrapper.Success(data = resp.getOrNull()))
        } else
            emit(ResponseWrapper.Error(message = resp.exceptionOrNull()?.message))

    }.catch { emit(ResponseWrapper.Error(message = it.message)) }
        .flowOn(Dispatchers.IO)
}