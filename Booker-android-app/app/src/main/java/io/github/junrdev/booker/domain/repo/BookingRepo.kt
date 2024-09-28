package io.github.junrdev.booker.domain.repo

import src.main.graphql.GetBookingByIdQuery
import src.main.graphql.GetBookingsQuery
import src.main.graphql.SaveBookingMutation
import src.main.graphql.type.BookingDto

interface BookingRepo {
    suspend fun getBookings(): Result<GetBookingsQuery.Data>
    suspend fun saveBooking(bookingDto: BookingDto): Result<SaveBookingMutation.Data>
    suspend fun getBookingById(id: String): Result<GetBookingByIdQuery.Data>
}