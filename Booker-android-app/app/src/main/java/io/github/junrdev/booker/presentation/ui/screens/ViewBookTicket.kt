package io.github.junrdev.booker.presentation.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import io.github.junrdev.booker.R
import io.github.junrdev.booker.databinding.FragmentViewBookTicketBinding


@AndroidEntryPoint
class ViewBookTicket : Fragment(){
    lateinit var binding : FragmentViewBookTicketBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentViewBookTicketBinding.inflate(inflater).also { binding=it }.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {


        }
    }
}