package io.github.junrdev.booker.domain.repo

import src.main.graphql.FetchScheduleByIdQuery
import src.main.graphql.FetchSchedulesQuery

interface SchedulesRepo {

    suspend fun getSchedules() : Result<FetchSchedulesQuery.Data>

    suspend fun getScheduleById(id : String) : Result<FetchScheduleByIdQuery.Data>

}