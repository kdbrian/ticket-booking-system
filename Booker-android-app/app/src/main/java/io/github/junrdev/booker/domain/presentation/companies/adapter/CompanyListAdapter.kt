package io.github.junrdev.booker.domain.presentation.companies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.junrdev.booker.R
import io.github.junrdev.booker.databinding.CompanypreviewBinding
import src.main.graphql.FetchCompaniesQuery

class CompanyListAdapter(
    private val companies: List<FetchCompaniesQuery.GetCompany>
) : BaseAdapter() {
    inner class VH(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(x: FetchCompaniesQuery.GetCompany) {
            val binding = CompanypreviewBinding.bind(view)
            binding.company = x
        }
    }

    override fun getCount(): Int {
        return companies.size
    }

    override fun getItem(p0: Int): Any {
        return companies[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        return CompanypreviewBinding.bind(
            LayoutInflater.from(p2?.context).inflate(R.layout.companypreview, null)
        ).apply {
            company = companies[p0]
        }.root
    }
}