package io.github.junrdev.booker.domain.use_cases

import io.github.junrdev.booker.data.util.ResponseWrapper
import io.github.junrdev.booker.domain.repo.VehiclesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import src.main.graphql.FetchVehicleByIdQuery
import src.main.graphql.FetchVehiclesBookingsQuery
import src.main.graphql.FetchVehiclesByRouteQuery
import src.main.graphql.FetchVehiclesQuery
import javax.inject.Inject

class VehiclesUseCase @Inject constructor(
    private val vehiclesRepo: VehiclesRepo
) {

    fun allVehiclesUseCase(): Flow<ResponseWrapper<FetchVehiclesQuery.Data>> = flow {
        emit(ResponseWrapper.Loading())

        val resp = vehiclesRepo.getVehicles()
        if (resp.isSuccess) {
            emit(ResponseWrapper.Success(data = resp.getOrNull()))
        } else
            emit(ResponseWrapper.Error(message = resp.exceptionOrNull()?.message))
    }.catch {
        emit(ResponseWrapper.Error(message = it.message))
    }.flowOn(Dispatchers.IO)


    fun vehicleByIdUseCase(id: String): Flow<ResponseWrapper<FetchVehicleByIdQuery.Data>> = flow {
        emit(ResponseWrapper.Loading())

        val resp = vehiclesRepo.getVehicleById(id)
        if (resp.isSuccess) {
            emit(ResponseWrapper.Success(data = resp.getOrNull()))
        } else
            emit(ResponseWrapper.Error(message = resp.exceptionOrNull()?.message))
    }.catch {
        emit(ResponseWrapper.Error(message = it.message))
    }.flowOn(Dispatchers.IO)

    fun vehicleByRouteUseCase(routeId: String): Flow<ResponseWrapper<FetchVehiclesByRouteQuery.Data>> =
        flow {
            emit(ResponseWrapper.Loading())

            val resp = vehiclesRepo.getRouteVehicles(routeId)
            if (resp.isSuccess) {
                emit(ResponseWrapper.Success(data = resp.getOrNull()))
            } else
                emit(ResponseWrapper.Error(message = resp.exceptionOrNull()?.message))
        }.catch {
            emit(ResponseWrapper.Error(message = it.message))
        }.flowOn(Dispatchers.IO)

    fun vehicleBookingsUseCase(bookingId: String): Flow<ResponseWrapper<FetchVehiclesBookingsQuery.Data>> =
        flow {
            emit(ResponseWrapper.Loading())

            val resp = vehiclesRepo.getVehiclesBookings(bookingId)
            if (resp.isSuccess) {
                emit(ResponseWrapper.Success(data = resp.getOrNull()))
            } else
                emit(ResponseWrapper.Error(message = resp.exceptionOrNull()?.message))
        }.catch {
            emit(ResponseWrapper.Error(message = it.message))
        }.flowOn(Dispatchers.IO)


}