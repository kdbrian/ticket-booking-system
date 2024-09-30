package io.github.junrdev.booker.domain.repo

import src.main.graphql.FetchVehicleByIdQuery
import src.main.graphql.FetchVehiclesBookingsQuery
import src.main.graphql.FetchVehiclesByRouteQuery
import src.main.graphql.FetchVehiclesQuery

interface VehiclesRepo {
    suspend fun getVehicles(): Result<FetchVehiclesQuery.Data>
    suspend fun getVehicleById(id: String): Result<FetchVehicleByIdQuery.Data>
    suspend fun getRouteVehicles(routeId: String): Result<FetchVehiclesByRouteQuery.Data>
    suspend fun getVehiclesBookings(bookingId: String): Result<FetchVehiclesBookingsQuery.Data>
}