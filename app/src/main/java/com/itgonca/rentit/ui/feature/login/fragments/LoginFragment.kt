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
import com.google.firebase.auth.AuthResult
import com.itgonca.rentit.databinding.FragmentLoginBinding
import com.itgonca.rentit.ui.feature.login.adapter.ScreensPagerAdapter
import com.itgonca.rentit.ui.feature.signin.CreateAccountFragment
import com.itgonca.rentit.ui.viewmodel.LoginViewModel
import com.itgonca.rentit.utils.functional.StateUi
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
        viewModel.registerUser.observe(viewLifecycleOwner, ::registerUser)
        viewModel.loginUser.observe(viewLifecycleOwner, ::loginUser)
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

    private fun registerUser(state: StateUi<AuthResult>) {
      /*  when (state) {
            StateUi.Loading -> activityContext().isShowLoader(true)
            is StateUi.Success -> {
                activityContext().isShowLoader(false)
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
            is StateUi.Error -> {
                activityContext().isShowLoader(false)
                showAlert("Error", "El usuario ya existe")
            }
        }*/
    }

    private fun loginUser(state: StateUi<AuthResult>) {
       /* when (state) {
            StateUi.Loading -> activityContext().isShowLoader(true)
            is StateUi.Success -> {
                activityContext().isShowLoader(false)
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
            is StateUi.Error -> {
                activityContext().isShowLoader(false)
                if(state.failure == FirebaseFailure.PasswordInvalid)
                    showAlert("Error","The password is invalid or the user does not have a password")
                else
                    showAlert("Error","User with this email not exist")
            }
        }*/
    }

}