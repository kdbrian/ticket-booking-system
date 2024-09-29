package io.github.junrdev.booker.data.repo

import com.apollographql.apollo.ApolloClient
import io.github.junrdev.booker.domain.repo.ClientsRepo
import src.main.graphql.GetClientByIdQuery
import src.main.graphql.GetClientsQuery
import javax.inject.Inject

class ClientsRepoImpl @Inject constructor(
    private val apolloClient: ApolloClient
) : ClientsRepo {
    override suspend fun getClient(id: String): Result<GetClientByIdQuery.Data> {
        return try {
            val resp = apolloClient.query(GetClientByIdQuery(id)).execute()
            resp.data?.let {
                Result.success(it)
            } ?: Result.failure(resp.exception!!)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getAllClients(): Result<GetClientsQuery.Data> {
        return try {
            val resp = apolloClient.query(GetClientsQuery()).execute()
            resp.data?.let {
                Result.success(it)
            } ?: Result.failure(resp.exception!!)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}