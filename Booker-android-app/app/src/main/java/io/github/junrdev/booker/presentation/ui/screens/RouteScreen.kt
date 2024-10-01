package io.github.junrdev.booker.presentation.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.junrdev.booker.R
import io.github.junrdev.booker.data.util.ResponseWrapper
import io.github.junrdev.booker.databinding.FragmentRouteScreenBinding
import io.github.junrdev.booker.domain.use_cases.RoutesUseCase
import io.github.junrdev.booker.presentation.adapter.RouteListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class RouteScreen : Fragment() {

    @Inject
    lateinit var routesUseCase: RoutesUseCase

    lateinit var binding: FragmentRouteScreenBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentRouteScreenBinding.inflate(inflater).also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch {

            routesUseCase.getAllRoutes().collect { responseWrapper ->
                when (responseWrapper) {
                    is ResponseWrapper.Error -> Toast.makeText(
                        requireContext(),
                        responseWrapper.message,
                        Toast.LENGTH_SHORT
                    ).show()

                    is ResponseWrapper.Loading -> Toast.makeText(
                        requireContext(),
                        "Loading please wait",
                        Toast.LENGTH_SHORT
                    ).show()

                    is ResponseWrapper.Success -> {
                        responseWrapper.data?.let { lst ->
                            binding.routeList.adapter =
                                RouteListAdapter(lst, lst.getAllRoutes!!.size) {
                                    findNavController().navigate(
                                        R.id.action_routeScreen_to_viewBookTicket,
                                        bundleOf("route" to it)
                                    )
                                }
                        }

                    }
                }
            }

        }

    }
}