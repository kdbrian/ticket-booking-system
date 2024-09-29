package io.github.junrdev.booker.domain.use_cases

import io.github.junrdev.booker.data.util.ResponseWrapper
import io.github.junrdev.booker.domain.repo.ClientsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import src.main.graphql.GetClientByIdQuery
import src.main.graphql.GetClientsQuery
import javax.inject.Inject

class ClientsUseCase @Inject constructor(
    private val clientsRepo: ClientsRepo
) {

    fun getAllClientsCase(): Flow<ResponseWrapper<GetClientsQuery.Data>> = flow {
        emit(ResponseWrapper.Loading())

        val resp = clientsRepo.getAllClients()

        if (resp.isSuccess)
            emit(ResponseWrapper.Success(data = resp.getOrNull()))
        else
            emit(ResponseWrapper.Error(message = resp.exceptionOrNull()?.message))
    }.catch { emit(ResponseWrapper.Error(message = it.message)) }
        .flowOn(Dispatchers.IO)

    fun getClientByIdCase(id: String): Flow<ResponseWrapper<GetClientByIdQuery.Data>> = flow {
        emit(ResponseWrapper.Loading())

        val resp = clientsRepo.getClient(id)

        if (resp.isSuccess)
            emit(ResponseWrapper.Success(data = resp.getOrNull()))
        else
            emit(ResponseWrapper.Error(message = resp.exceptionOrNull()?.message))
    }.catch { emit(ResponseWrapper.Error(message = it.message)) }
        .flowOn(Dispatchers.IO)

}