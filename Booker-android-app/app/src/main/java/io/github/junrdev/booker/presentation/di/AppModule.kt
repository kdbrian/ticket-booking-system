package io.github.junrdev.booker.presentation.di

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.network.okHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.junrdev.booker.data.repo.BookingRepoImpl
import io.github.junrdev.booker.data.repo.CompaniesRepoImpl
import io.github.junrdev.booker.data.repo.RouteRepoImpl
import io.github.junrdev.booker.data.repo.SchedulesRepoImpl
import io.github.junrdev.booker.domain.repo.BookingRepo
import io.github.junrdev.booker.domain.repo.CompaniesRepo
import io.github.junrdev.booker.domain.repo.RouteRepo
import io.github.junrdev.booker.domain.repo.SchedulesRepo
import io.github.junrdev.booker.domain.use_cases.BookingsUseCase
import io.github.junrdev.booker.domain.use_cases.CompaniesUseCase
import io.github.junrdev.booker.domain.use_cases.RoutesUseCase
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun providesOkHttpClient() = OkHttpClient.Builder().build()

    @Singleton
    @Provides
    fun providesApolloClient(okHttpClient: OkHttpClient): ApolloClient {
        return ApolloClient
            .Builder()
            .serverUrl("http://10.0.2.2:9091/bookerapi/graphql")
            .okHttpClient(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun providesCompaniesRepository(apolloClient: ApolloClient): CompaniesRepo {
        return CompaniesRepoImpl(apolloClient)
    }

    @Singleton
    @Provides
    fun providesRouteRepository(apolloClient: ApolloClient): RouteRepo {
        return RouteRepoImpl(apolloClient)
    }

    @Singleton
    @Provides
    fun providesScheduleRepository(apolloClient: ApolloClient): SchedulesRepo {
        return SchedulesRepoImpl(apolloClient)
    }


    @Singleton
    @Provides
    fun providesBookingRepository(apolloClient: ApolloClient): BookingRepo {
        return BookingRepoImpl(apolloClient)
    }


    @Singleton
    @Provides
    fun providesRoutesUseCase(repo: RouteRepo): RoutesUseCase {
        return RoutesUseCase(repo)
    }

    @Singleton
    @Provides
    fun providesCompaniesUseCase(companiesRepo: CompaniesRepo): CompaniesUseCase {
        return CompaniesUseCase(companiesRepo)
    }


    @Singleton
    @Provides
    fun providesBookingUseCase(bookingRepo: BookingRepo): BookingsUseCase {
        return BookingsUseCase(bookingRepo)
    }

}