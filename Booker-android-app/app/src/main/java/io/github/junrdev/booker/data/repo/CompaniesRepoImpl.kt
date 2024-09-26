package io.github.junrdev.booker.data.repo

import com.apollographql.apollo.ApolloClient
import io.github.junrdev.booker.domain.repo.CompaniesRepo
import src.main.graphql.FetchCompaniesQuery
import src.main.graphql.FetchCompanyByIdQuery
import javax.inject.Inject

class CompaniesRepoImpl @Inject constructor(
    private val apolloClient: ApolloClient
) : CompaniesRepo {

    override suspend fun getCompanies(): Result<FetchCompaniesQuery.Data> {
        return try {
            val resp = apolloClient.query(FetchCompaniesQuery()).execute()
            resp.data?.let {
                Result.success(it)
            } ?: run { Result.failure(resp.exception!!) }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCompanyById(id: String): Result<FetchCompanyByIdQuery.Data> {
        return try {
            val resp = apolloClient.query(FetchCompanyByIdQuery()).execute()
            resp.data?.let {
                Result.success(it)
            } ?: run { Result.failure(resp.exception!!) }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}