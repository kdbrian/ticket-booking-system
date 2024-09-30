package io.github.junrdev.booker.data.util

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class SearchPrompt(
    val query: String?=null,
    val date: Date?=null,
    val countyNumber: Int?=null,
    val subCounty: String?=null,
    val from: String?=null,
    val to: String?=null,
    val priceRangeFrom: Double?=null,
    val priceRangeTo: Double?=null
) : Parcelable {


    companion object {
        fun getQuery(query: String) =
            if (query.startsWith("route:"))
                query.substringAfter("route:")
            else if (query.startsWith("price:"))
                query.substringAfter("price:")
            else
                query
    }
}
