package com.itgonca.rentit.ui.feature.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.firebase.auth.AuthResult
import com.itgonca.rentit.databinding.FormCreateAccountBinding
import com.itgonca.rentit.ui.viewmodel.LoginViewModel
import com.itgonca.rentit.utils.extension.activityContext
import com.itgonca.rentit.utils.extension.showAlert
import com.itgonca.rentit.utils.functional.StateUi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateAccountFragment : Fragment() {

    private var _binding: FormCreateAccountBinding? = null
    private val binding
        get() = _binding!!

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
        initObserver()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initUi() {
        binding.btnRegister.setOnClickListener {
            viewModel.register(
                binding.etEnterEmail.text.toString(),
                binding.etEnterPassword.text.toString()
            )
        }
    }

    private fun initObserver() {
        viewModel.registerUser.observe(viewLifecycleOwner, ::registerUser)
    }

    private fun registerUser(state: StateUi<AuthResult>) {
        when (state) {
            StateUi.Loading -> activityContext().isShowLoader(true)
            is StateUi.Success -> {
                activityContext().isShowLoader(false)
            }
            is StateUi.Error -> {
                activityContext().isShowLoader(false)
                showAlert("Error", "El usuario ya existe")
            }
        }
    }
}