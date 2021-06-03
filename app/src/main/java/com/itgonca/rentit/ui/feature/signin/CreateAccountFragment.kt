package com.itgonca.rentit.ui.feature.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.itgonca.rentit.R
import com.itgonca.rentit.databinding.FormCreateAccountBinding
import com.itgonca.rentit.ui.viewmodel.LoginViewModel
import com.itgonca.rentit.utils.extension.isValidEmail
import com.itgonca.rentit.utils.extension.resourceDrawable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateAccountFragment : Fragment() {

    private var _binding: FormCreateAccountBinding? = null
    private val binding
        get() = _binding!!

    var email: String = ""
    var password: String = ""

    private val viewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FormCreateAccountBinding.inflate(inflater, container, false)
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
        binding.btnRegister.setOnClickListener {
            viewModel.register(email, password)
        }
        binding.ivArrowForward.setOnClickListener {
            viewModel.actionNextPage(1)
        }
        binding.tilEmail.editText?.doAfterTextChanged { paramEmail ->
            email = paramEmail?.toString() ?: ""
            validateTextFields()
        }
        binding.tilPassword.editText?.doAfterTextChanged { paramPassword ->
            password = paramPassword?.toString() ?: ""
            validateTextFields()
        }
    }

    private fun validateTextFields() {
        val isValidEmail = email.isValidEmail() && password.isNotEmpty()
        val isValidPassword = if (password.length < 6 && password.isNotEmpty()) {
            binding.tilPassword.error = getString(R.string.password_length_error_label)
            false
        } else {
            binding.tilPassword.error = null
            true
        }
        binding.btnRegister.isEnabled = isValidEmail && isValidPassword
        binding.tilEmail.endIconDrawable =
            if (email.isValidEmail()) resourceDrawable(R.drawable.ic_green_check_circle) else null
    }
}