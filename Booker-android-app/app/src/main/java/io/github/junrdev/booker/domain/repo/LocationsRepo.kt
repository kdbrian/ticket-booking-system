package io.github.junrdev.booker.domain.repo

import src.main.graphql.FetchCountiesQuery
import src.main.graphql.FetchSubCountiesQuery
import src.main.graphql.FetchSubCountyByCountyNameQuery
import src.main.graphql.FetchSubCountyByCountyNumberQuery

interface LocationsRepo {
    suspend fun getCounties(): Result<FetchCountiesQuery.Data>
    suspend fun getSubCounties(): Result<FetchSubCountiesQuery.Data>
    suspend fun getSubCountiesByCountyName(name: String): Result<FetchSubCountyByCountyNameQuery.Data>
    suspend fun getSubCountiesByCountyNumber(num: Int): Result<FetchSubCountyByCountyNumberQuery.Data>
}