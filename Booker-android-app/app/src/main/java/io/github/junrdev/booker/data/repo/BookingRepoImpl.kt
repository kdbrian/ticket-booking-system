package io.github.junrdev.booker.data.repo

import com.apollographql.apollo.ApolloClient
import io.github.junrdev.booker.domain.repo.BookingRepo
import src.main.graphql.GetBookingByIdQuery
import src.main.graphql.GetBookingsQuery
import src.main.graphql.SaveBookingMutation
import src.main.graphql.type.BookingDto
import javax.inject.Inject

class BookingRepoImpl @Inject constructor(
    private val apolloClient: ApolloClient
) : BookingRepo {


    override suspend fun getBookings(): Result<GetBookingsQuery.Data> {
        return try {
            val resp = apolloClient.query(GetBookingsQuery()).execute()
            resp.data?.let {
                Result.success(it)
            } ?: run {
                Result.failure(resp.exception!!)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getBookingById(id: String): Result<GetBookingByIdQuery.Data> {
        return try {
            val resp = apolloClient.query(GetBookingByIdQuery(id)).execute()
            resp.data?.let {
                Result.success(it)
            } ?: run {
                Result.failure(resp.exception!!)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun saveBooking(bookingDto: BookingDto): Result<SaveBookingMutation.Data> {
        return try {
            val resp = apolloClient.mutation(SaveBookingMutation(bookingDto)).execute()
            resp.data?.let {
                Result.success(it)
            } ?: run {
                Result.failure(resp.exception!!)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}