package io.github.junrdev.booker.domain.repo

import src.main.graphql.FetchCompaniesQuery
import src.main.graphql.FetchCompanyByIdQuery

interface CompaniesRepo {

    suspend fun getCompanies () : Result<FetchCompaniesQuery.Data>

    suspend fun getCompanyById(id : String) : Result<FetchCompanyByIdQuery.Data>

}