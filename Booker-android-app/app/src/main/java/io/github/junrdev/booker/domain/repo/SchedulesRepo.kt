package io.github.junrdev.booker.domain.repo

import src.main.graphql.FetchScheduleByIdQuery
import src.main.graphql.FetchSchedulesQuery

interface SchedulesRepo {

    suspend fun getSchedules() : FetchSchedulesQuery.Data

    suspend fun getScheduleById() : FetchScheduleByIdQuery.Data

}