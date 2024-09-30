package io.github.junrdev.booker.data.repo

import com.apollographql.apollo.ApolloClient
import io.github.junrdev.booker.domain.repo.VehiclesRepo
import src.main.graphql.FetchVehicleByIdQuery
import src.main.graphql.FetchVehiclesBookingsQuery
import src.main.graphql.FetchVehiclesByRouteQuery
import src.main.graphql.FetchVehiclesQuery
import javax.inject.Inject

class VehiclesRepoImpl @Inject constructor(
    private val apolloClient: ApolloClient
) : VehiclesRepo {

    override suspend fun getVehicles(): Result<FetchVehiclesQuery.Data> {
        return try {
            val resp = apolloClient.query(FetchVehiclesQuery()).execute()
            resp.data?.let {
                Result.success(it)
            } ?: Result.failure(resp.exception!!)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getVehicleById(id: String): Result<FetchVehicleByIdQuery.Data> {
        return try {
            val resp = apolloClient.query(FetchVehicleByIdQuery(id)).execute()
            resp.data?.let {
                Result.success(it)
            } ?: Result.failure(resp.exception!!)
        } catch (e: Exception) {
            Result.failure(e)
        }    }

    override suspend fun getRouteVehicles(routeId: String): Result<FetchVehiclesByRouteQuery.Data> {
        return try {
            val resp = apolloClient.query(FetchVehiclesByRouteQuery(routeId)).execute()
            resp.data?.let {
                Result.success(it)
            } ?: Result.failure(resp.exception!!)
        } catch (e: Exception) {
            Result.failure(e)
        }    }

    override suspend fun getVehiclesBookings(bookingId: String): Result<FetchVehiclesBookingsQuery.Data> {
        return try {
            val resp = apolloClient.query(FetchVehiclesBookingsQuery(bookingId)).execute()
            resp.data?.let {
                Result.success(it)
            } ?: Result.failure(resp.exception!!)
        } catch (e: Exception) {
            Result.failure(e)
        }    }
}