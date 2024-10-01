package io.github.junrdev.booker.presentation.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.junrdev.booker.databinding.FragmentViewBookTicketBinding
import io.github.junrdev.booker.presentation.viewmodel.RoutesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ViewBookTicket : Fragment() {


    lateinit var binding: FragmentViewBookTicketBinding
    private var routeId: String? = null
    private val routesViewModel by viewModels<RoutesViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentViewBookTicketBinding.inflate(inflater).also { binding = it }.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            arguments?.let { bundle ->
                bundle.getString("route")?.also {
                    routeId = it
                }
            }

            CoroutineScope(Dispatchers.Main).launch {

                routeId?.let {
                    routesViewModel.getRouteById(it)
                }


                routesViewModel.routesByIdUiState.collect { state ->

                    if (state.isLoading) {
                        showToast("Loading please wait.")
                    }


                    if (state.data != null) {
                        delay(800L)
                        route = state.data

                        println("Details ${state.data.getRouteById}")
                        val vehicles = state.data.getRouteById.vehicles
                    }

                    if (state.error != null) {
                        showToast("Failed to get route")
                    }

                }

            }

            imageView6.setOnClickListener {
                findNavController().navigateUp()
            }


        }
    }


    private fun showToast(message: String) =
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}