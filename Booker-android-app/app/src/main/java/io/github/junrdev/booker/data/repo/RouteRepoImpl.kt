package io.github.junrdev.booker.data.repo

import com.apollographql.apollo.ApolloClient
import io.github.junrdev.booker.domain.repo.RouteRepo
import src.main.graphql.GetAllRoutesQuery
import src.main.graphql.GetRouteByIdQuery
import src.main.graphql.SaveRoutesMutation
import src.main.graphql.type.RouteDto


class RouteRepoImpl
//@Inject constructor
    (
    private val apolloClient: ApolloClient
) : RouteRepo {

    override suspend fun getAllRoutes(): Result<GetAllRoutesQuery.Data> {
        return try {
            val resp = apolloClient.query(GetAllRoutesQuery()).execute()
            resp.data?.let {
                Result.success(it)
            } ?: Result.failure(resp.exception!!)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getRouteById(id: String): Result<GetRouteByIdQuery.Data> {
        return try {
            val resp = apolloClient.query(GetRouteByIdQuery(id)).execute()
            resp.data?.let {
                Result.success(it)
            } ?: Result.failure(resp.exception!!)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun addRoute(dto: RouteDto): Result<SaveRoutesMutation.Data> {
        return try {
            val resp = apolloClient.mutation(SaveRoutesMutation(dto)).execute()
            resp.data?.let {
                Result.success(it)
            } ?: Result.failure(resp.exception!!)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}