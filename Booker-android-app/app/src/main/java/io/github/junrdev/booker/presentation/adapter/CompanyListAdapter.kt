package io.github.junrdev.booker.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.junrdev.booker.R
import io.github.junrdev.booker.databinding.CompanypreviewBinding
import src.main.graphql.FetchCompaniesQuery

class CompanyListAdapter(
    private val companies: FetchCompaniesQuery.Data
) : RecyclerView.Adapter<CompanyListAdapter.VH>() {

    inner class VH(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(x: FetchCompaniesQuery.GetCompany) {
            CompanypreviewBinding.bind(view).apply {
                company = x
            }.root
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.companypreview, null)
        return VH(view)
    }

    override fun getItemCount(): Int {
        return companies.getCompanies!!.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(companies.getCompanies!![position])
    }
}