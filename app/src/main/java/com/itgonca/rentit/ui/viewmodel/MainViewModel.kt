package com.itgonca.rentit.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")
    private val _step = MutableStateFlow(1)

    val email: StateFlow<String>
        get() = _email
    val password: StateFlow<String>
        get() = _password
    val step: StateFlow<Int>
        get() = _step

    //Events
    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun onStepChange(newStep: Int) {
        _step.value = newStep
    }
}