package io.github.junrdev.booker.data.repo

import com.apollographql.apollo.ApolloClient
import io.github.junrdev.booker.domain.repo.SchedulesRepo
import src.main.graphql.FetchScheduleByIdQuery
import src.main.graphql.FetchSchedulesQuery
import javax.inject.Inject

class SchedulesRepoImpl @Inject constructor(
    private val apolloClient: ApolloClient
) : SchedulesRepo {

    override suspend fun getSchedules(): Result<FetchSchedulesQuery.Data> {
        return try {
            val resp = apolloClient.query(FetchSchedulesQuery()).execute()
            resp.data?.let {
                Result.success(it)
            } ?: Result.failure(resp.exception!!)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getScheduleById(id: String): Result<FetchScheduleByIdQuery.Data> {
        return try {
            val resp = apolloClient.query(FetchScheduleByIdQuery(id = id)).execute()
            resp.data?.let {
                Result.success(it)
            } ?: Result.failure(resp.exception!!)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}