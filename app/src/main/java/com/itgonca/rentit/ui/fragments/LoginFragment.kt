package com.itgonca.rentit.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.itgonca.rentit.databinding.FragmentLoginBinding
import com.itgonca.rentit.ui.adapter.ScreensPagerAdapter
import com.itgonca.rentit.ui.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel:LoginViewModel by activityViewModels()

    private lateinit var fragmentAdapter: ScreensPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val fragmentList = arrayListOf<Fragment>(
            EmailFragment(), PasswordFragment()
        )
        fragmentAdapter = ScreensPagerAdapter(fragmentList, childFragmentManager, lifecycle)
        _binding!!.viewPagerContainer.adapter = fragmentAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPagerContainer.isUserInputEnabled = false
        viewModel.nextPage.observe(viewLifecycleOwner,{
            binding.viewPagerContainer.currentItem = it
        })
        viewModel.previousPage.observe(viewLifecycleOwner,{
            binding.viewPagerContainer.currentItem = it
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val NUM_PAGES = 2
    }
}