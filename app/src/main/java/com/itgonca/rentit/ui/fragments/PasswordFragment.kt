package com.itgonca.rentit.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.itgonca.rentit.databinding.FormEmailLayoutBinding
import com.itgonca.rentit.databinding.FormPasswordLayoutBinding
import com.itgonca.rentit.ui.viewmodel.LoginViewModel

class PasswordFragment : Fragment() {
    private var _binding: FormPasswordLayoutBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FormPasswordLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCreateAccount.setOnClickListener {
            viewModel.actionPreviousPage(0)
        }
        binding.ivArrowBack.setOnClickListener {
            viewModel.actionPreviousPage(0)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}