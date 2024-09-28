package io.github.junrdev.booker.data.repo

import com.apollographql.apollo.ApolloClient
import io.github.junrdev.booker.domain.repo.LocationsRepo
import src.main.graphql.FetchCountiesQuery
import src.main.graphql.FetchSubCountiesQuery
import src.main.graphql.FetchSubCountyByCountyNameQuery
import src.main.graphql.FetchSubCountyByCountyNumberQuery
import javax.inject.Inject

class LocationsRepoImpl @Inject constructor(
    private val apolloClient: ApolloClient
) : LocationsRepo {

    override suspend fun getCounties(): Result<FetchCountiesQuery.Data> {
        val res = apolloClient.query(FetchCountiesQuery()).execute()
        return res.data?.let {
            Result.success(it)
        } ?: Result.failure(res.exception!!)
    }

    override suspend fun getSubCounties(): Result<FetchSubCountiesQuery.Data> {
        val res = apolloClient.query(FetchSubCountiesQuery()).execute()
        return res.data?.let {
            Result.success(it)
        } ?: Result.failure(res.exception!!)    }

    override suspend fun getSubCountiesByCountyName(name: String): Result<FetchSubCountyByCountyNameQuery.Data> {
        val res = apolloClient.query(FetchSubCountyByCountyNameQuery(name)).execute()
        return res.data?.let {
            Result.success(it)
        } ?: Result.failure(res.exception!!)    }

    override suspend fun getSubCountiesByCountyNumber(num: Int): Result<FetchSubCountyByCountyNumberQuery.Data> {
        val res = apolloClient.query(FetchSubCountyByCountyNumberQuery(num)).execute()
        return res.data?.let {
            Result.success(it)
        } ?: Result.failure(res.exception!!)    }
}