package io.github.junrdev.booker.domain.use_cases

import io.github.junrdev.booker.domain.repo.CompaniesRepo
import io.github.junrdev.booker.data.util.ResponseWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import src.main.graphql.FetchCompaniesQuery
import src.main.graphql.FetchCompanyByIdQuery
import javax.inject.Inject

class CompaniesUseCase @Inject constructor(
    private val companiesRepo: CompaniesRepo
) {

    fun getCompanies(): Flow<ResponseWrapper<FetchCompaniesQuery.Data>> = flow {
        emit(ResponseWrapper.Loading())
        val resp = companiesRepo.getCompanies()

        if (resp.isSuccess) {
            emit(ResponseWrapper.Success(data = resp.getOrNull()))
        } else
            emit(ResponseWrapper.Error(resp.exceptionOrNull()?.message))
    }
        .catch { emit(ResponseWrapper.Error(message = it.message)) }
        .flowOn(Dispatchers.IO)


    fun getCompanyById(id: String): Flow<ResponseWrapper<FetchCompanyByIdQuery.Data>> = flow {

        emit(ResponseWrapper.Loading())

        val resp = companiesRepo.getCompanyById(id)

        if (resp.isSuccess) {
            emit(ResponseWrapper.Success(data = resp.getOrNull()))
        } else
            emit(ResponseWrapper.Error(resp.exceptionOrNull()?.message))
    }
        .catch { emit(ResponseWrapper.Error(message = it.message)) }
        .flowOn(Dispatchers.IO)


}