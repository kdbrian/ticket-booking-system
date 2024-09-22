package io.github.junrdev.booker.domain.presentation.routes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import io.github.junrdev.booker.R
import io.github.junrdev.booker.databinding.RouteitemBinding
import src.main.graphql.GetAllRoutesQuery

class RouteListAdapter(
    val routes: GetAllRoutesQuery.Data
) : BaseAdapter() {
    override fun getCount(): Int {
        return routes.getAllRoutes!!.size
    }

    override fun getItem(p0: Int): Any {
        return routes.getAllRoutes!![p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        val fetchedRoute = routes.getAllRoutes!![p0]
        val view = LayoutInflater.from(p2?.context).inflate(R.layout.routeitem, null)

        RouteitemBinding.bind(view).apply {
            route = fetchedRoute
        }

        return view
    }
}