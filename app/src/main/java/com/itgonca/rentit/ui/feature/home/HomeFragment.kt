package com.itgonca.rentit.ui.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.itgonca.rentit.R
import com.itgonca.rentit.databinding.FragmentHomeBinding
import com.itgonca.rentit.ui.viewmodel.HomeViewModel
import com.itgonca.rentit.ui.viewmodel.LoginViewModel
import com.itgonca.rentit.utils.extension.activityContext
import com.itgonca.rentit.utils.functional.StateUi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!
    private val loginViewModel: LoginViewModel by activityViewModels()
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        homeViewModel.getListLocations()
    }

    private fun initObserver() {
        loginViewModel.isSessionActive.observe(viewLifecycleOwner, ::validateSession)
    }

    private fun validateSession(state: StateUi<Boolean>) {
        when (state) {
            StateUi.Loading -> activityContext().isShowLoader(true)
            is StateUi.Success -> {
                activityContext().isShowLoader(false)
                if (!state.data) {
                    findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
                }
            }
            is StateUi.Error -> activityContext().isShowLoader(false)
        }
    }
}