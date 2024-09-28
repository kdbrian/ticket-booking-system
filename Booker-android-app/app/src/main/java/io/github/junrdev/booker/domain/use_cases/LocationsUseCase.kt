package io.github.junrdev.booker.domain.use_cases

import io.github.junrdev.booker.data.util.ResponseWrapper
import io.github.junrdev.booker.domain.repo.LocationsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import src.main.graphql.FetchCountiesQuery
import src.main.graphql.FetchSubCountiesQuery
import src.main.graphql.FetchSubCountyByCountyNameQuery
import src.main.graphql.FetchSubCountyByCountyNumberQuery
import javax.inject.Inject

class LocationsUseCase @Inject constructor(
    private val locationsRepo: LocationsRepo
) {

    fun getCounties(): Flow<ResponseWrapper<FetchCountiesQuery.Data>> = flow {
        emit(ResponseWrapper.Loading())

        val resp = locationsRepo.getCounties()
        if (resp.isSuccess) {
            emit(ResponseWrapper.Success(data = resp.getOrNull()))
        } else
            emit(ResponseWrapper.Error(message = resp.exceptionOrNull()?.message))
    }.catch { emit(ResponseWrapper.Error(message = it.message)) }
        .flowOn(Dispatchers.IO)


    fun getSubCounties(): Flow<ResponseWrapper<FetchSubCountiesQuery.Data>> = flow {
        emit(ResponseWrapper.Loading())

        val resp = locationsRepo.getSubCounties()
        if (resp.isSuccess) {
            emit(ResponseWrapper.Success(data = resp.getOrNull()))
        } else
            emit(ResponseWrapper.Error(message = resp.exceptionOrNull()?.message))
    }.catch { emit(ResponseWrapper.Error(message = it.message)) }
        .flowOn(Dispatchers.IO)


    fun getSubCountiesByCountyName(name: String): Flow<ResponseWrapper<FetchSubCountyByCountyNameQuery.Data>> =
        flow {
            emit(ResponseWrapper.Loading())

            val resp = locationsRepo.getSubCountiesByCountyName(name)
            if (resp.isSuccess) {
                emit(ResponseWrapper.Success(data = resp.getOrNull()))
            } else
                emit(ResponseWrapper.Error(message = resp.exceptionOrNull()?.message))
        }.catch { emit(ResponseWrapper.Error(message = it.message)) }
            .flowOn(Dispatchers.IO)


    fun getSubCountiesByCountyNumber(num: Int): Flow<ResponseWrapper<FetchSubCountyByCountyNumberQuery.Data>> =
        flow {
            emit(ResponseWrapper.Loading())

            val resp = locationsRepo.getSubCountiesByCountyNumber(num)
            if (resp.isSuccess) {
                emit(ResponseWrapper.Success(data = resp.getOrNull()))
            } else
                emit(ResponseWrapper.Error(message = resp.exceptionOrNull()?.message))
        }.catch { emit(ResponseWrapper.Error(message = it.message)) }
            .flowOn(Dispatchers.IO)


}