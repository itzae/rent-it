package com.itgonca.rentit.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.itgonca.rentit.databinding.FormEmailLayoutBinding
import com.itgonca.rentit.ui.viewmodel.LoginViewModel

class EmailFragment : Fragment() {
    private var _binding: FormEmailLayoutBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FormEmailLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNextStep.setOnClickListener {
            viewModel.actionNextPage(1)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}