package io.github.junrdev.booker.domain.use_cases

import io.github.junrdev.booker.domain.repo.BookingRepo
import io.github.junrdev.booker.data.util.ResponseWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import src.main.graphql.GetBookingsQuery
import javax.inject.Inject

class BookingsUseCase @Inject constructor(
    private val bookingRepo: BookingRepo
) {

    fun getBookingsUseCase(): Flow<ResponseWrapper<GetBookingsQuery.Data>> = flow {
        emit(ResponseWrapper.Loading())

        val resp = bookingRepo.getBookings()
        if (resp.isSuccess) {
            emit(ResponseWrapper.Success(data = resp.getOrNull()))
        } else
            emit(ResponseWrapper.Error(message = resp.exceptionOrNull()?.message))
    }
        .catch { emit(ResponseWrapper.Error(message = it.message)) }
        .flowOn(Dispatchers.IO)

}