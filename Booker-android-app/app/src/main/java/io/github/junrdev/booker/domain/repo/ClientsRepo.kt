package io.github.junrdev.booker.domain.repo

import src.main.graphql.GetClientByIdQuery
import src.main.graphql.GetClientsQuery

interface ClientsRepo {
    suspend fun getClient(id: String): Result<GetClientByIdQuery.Data>
    suspend fun getAllClients(): Result<GetClientsQuery.Data>
}