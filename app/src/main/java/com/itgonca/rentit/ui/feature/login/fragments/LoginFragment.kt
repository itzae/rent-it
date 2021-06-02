package com.itgonca.rentit.ui.feature.login.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.findNavController
import com.itgonca.rentit.databinding.FragmentLoginBinding
import com.itgonca.rentit.ui.feature.login.adapter.ScreensPagerAdapter
import com.itgonca.rentit.ui.feature.signin.CreateAccountFragment
import com.itgonca.rentit.ui.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    companion object {
        const val LOGIN_SUCCESSFUL: String = "LOGIN_SUCCESSFUL"
    }

    private var _binding: FragmentLoginBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var savedStateHandle: SavedStateHandle

    private val viewModel: LoginViewModel by activityViewModels()

    private lateinit var fragmentAdapter: ScreensPagerAdapter

    private val callBackDispatcher = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            activity?.finish()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().onBackPressedDispatcher.addCallback(this, callBackDispatcher)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        initViewPager()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        initNavigation()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initObserver() {
        viewModel.nextPage.observe(viewLifecycleOwner, ::navigateViewPager)
        viewModel.previousPage.observe(viewLifecycleOwner, ::navigateViewPager)
    }

    private fun initViewPager() {
        val fragmentList = arrayListOf(
            CreateAccountFragment(), EmailFragment(), PasswordFragment()
        )
        fragmentAdapter = ScreensPagerAdapter(fragmentList, childFragmentManager, lifecycle)
        _binding!!.viewPagerContainer.run {
            adapter = fragmentAdapter
            currentItem = 1
            isUserInputEnabled = false
        }
    }

    private fun initNavigation() {
        savedStateHandle = findNavController().previousBackStackEntry!!.savedStateHandle
        savedStateHandle.set(LOGIN_SUCCESSFUL, false)
    }

    private fun navigateViewPager(page: Int) {
        binding.viewPagerContainer.currentItem = page
    }

}