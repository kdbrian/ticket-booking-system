package io.github.junrdev.booker.domain.repo

import src.main.graphql.GetBookingsQuery

interface BookingRepo {
    suspend fun getBookings() : Result<GetBookingsQuery.Data>
}