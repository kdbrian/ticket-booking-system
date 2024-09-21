package io.github.junrdev.booker.domain

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.network.okHttpClient
import io.github.junrdev.booker.data.repo.CompaniesRepoImpl
import io.github.junrdev.booker.domain.repo.CompaniesRepo
import io.github.junrdev.booker.domain.use_cases.CompaniesUseCase
import okhttp3.OkHttpClient

object Provider {

    fun provideApolloClient(): ApolloClient {
        val okHttpClient = OkHttpClient.Builder().build()
        return ApolloClient
            .Builder()
            .serverUrl("http://10.0.2.2:9091/bookerapi/graphql")
            .okHttpClient(okHttpClient)
            .build()
    }

    fun provideCompaniesRepo(apolloClient: ApolloClient = provideApolloClient()): CompaniesRepo {
        return CompaniesRepoImpl(apolloClient)
    }

    fun provideCompaniesUseCase(companiesRepo: CompaniesRepo = provideCompaniesRepo()): CompaniesUseCase {
        return CompaniesUseCase(companiesRepo)
    }
}