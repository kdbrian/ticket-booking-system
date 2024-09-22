package io.github.junrdev.booker.domain.presentation.routes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import io.github.junrdev.booker.databinding.FragmentRouteScreenBinding
import io.github.junrdev.booker.domain.use_cases.RoutesUseCase
import io.github.junrdev.booker.util.ResponseWrapper
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

            routesUseCase.getAllRoutes().collect {
                when (it) {
                    is ResponseWrapper.Error -> Toast.makeText(
                        requireContext(),
                        it.message,
                        Toast.LENGTH_SHORT
                    ).show()

                    is ResponseWrapper.Loading -> Toast.makeText(
                        requireContext(),
                        "Loading please wait",
                        Toast.LENGTH_SHORT
                    ).show()

                    is ResponseWrapper.Success -> {
                        it.data?.let { lst ->
                            binding.routeList.adapter = RouteListAdapter(lst)
                        }

                    }
                }
            }

        }

    }
}