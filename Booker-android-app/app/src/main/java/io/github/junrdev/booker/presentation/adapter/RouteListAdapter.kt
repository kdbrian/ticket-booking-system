package io.github.junrdev.booker.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.junrdev.booker.R
import io.github.junrdev.booker.databinding.RouteitemBinding
import src.main.graphql.GetAllRoutesQuery

class RouteListAdapter(
    val routes: GetAllRoutesQuery.Data,
    val count: Int = 2
) : RecyclerView.Adapter<RouteListAdapter.VH>() {

    inner class VH(val view: View) : RecyclerView.ViewHolder(view) {

        fun bindItem(currentRoute: GetAllRoutesQuery.GetAllRoute) {
            view.apply {
                RouteitemBinding.bind(view).also { it.route = currentRoute }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.routeitem, null)
        return VH(view)
    }

    override fun getItemCount(): Int {
        return if (routes.getAllRoutes!!.size > count)
            count
        else
            routes.getAllRoutes.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bindItem(
            currentRoute = routes.getAllRoutes!![position]
        )
    }
}