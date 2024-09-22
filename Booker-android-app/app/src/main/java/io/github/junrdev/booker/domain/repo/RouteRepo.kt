package io.github.junrdev.booker.domain.repo

import src.main.graphql.GetAllRoutesQuery
import src.main.graphql.GetRouteByIdQuery
import src.main.graphql.SaveRoutesMutation
import src.main.graphql.type.Route
import src.main.graphql.type.RouteDto

interface RouteRepo {

    suspend fun getAllRoutes() : Result<GetAllRoutesQuery.Data>

    suspend fun getRouteById(id : String) : Result<GetRouteByIdQuery.Data>

    suspend fun addRoute(dto : RouteDto) : Result<SaveRoutesMutation.Data>
}