package io.github.junrdev.booker.presentation.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.junrdev.booker.databinding.FragmentSearchResultsBinding

class SearchResults : Fragment() {
    lateinit var binding: FragmentSearchResultsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentSearchResultsBinding.inflate(inflater).also { binding = it }.root
    }



}