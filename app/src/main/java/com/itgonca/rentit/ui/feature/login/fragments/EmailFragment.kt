package com.itgonca.rentit.ui.feature.login.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.itgonca.rentit.R
import com.itgonca.rentit.databinding.FormEmailLayoutBinding
import com.itgonca.rentit.ui.viewmodel.LoginViewModel
import com.itgonca.rentit.utils.extension.isValidEmail
import com.itgonca.rentit.utils.extension.resourceDrawable

class EmailFragment : Fragment() {
    private var _binding: FormEmailLayoutBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FormEmailLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initUi() {
        binding.tilEmail.editText?.doAfterTextChanged {
            it?.let { textEmail ->
                binding.btnNextStep.isEnabled = textEmail.toString().isValidEmail() == true
                binding.tilEmail.endIconDrawable =
                    if (textEmail
                            .toString()
                            .isValidEmail()
                    ) resourceDrawable(R.drawable.ic_green_check_circle) else null
            }
        }
        binding.btnNextStep.setOnClickListener {
            viewModel.nextStepLogin(binding.tilEmail.editText?.text.toString())
            viewModel.actionNextPage(2)
        }
        binding.btnCreateAccount.setOnClickListener {
            viewModel.actionPreviousPage(0)
        }
    }
}