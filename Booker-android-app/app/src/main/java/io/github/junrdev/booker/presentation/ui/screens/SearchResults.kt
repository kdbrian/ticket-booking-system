package io.github.junrdev.booker.presentation.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.junrdev.booker.R
import io.github.junrdev.booker.databinding.FragmentSearchResultsBinding

@AndroidEntryPoint
class SearchResults : Fragment() {
    lateinit var binding: FragmentSearchResultsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentSearchResultsBinding.inflate(inflater).also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            imageView5.setOnClickListener{
                findNavController().navigate(R.id.action_searchResults_to_searchScreen)
            }

            imageView4.setOnClickListener{
                findNavController().popBackStack()
            }


        }
    }


}