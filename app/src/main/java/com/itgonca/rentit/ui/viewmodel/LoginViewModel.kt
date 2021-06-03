package com.itgonca.rentit.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.itgonca.rentit.domain.repository.FirebaseAuthRepository
import com.itgonca.rentit.utils.functional.Failure
import com.itgonca.rentit.utils.functional.StateUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val firebaseAuthRepository: FirebaseAuthRepository) :
    ViewModel() {

    private var _nextPage = MutableLiveData<Int>()
    val nextPage: LiveData<Int>
        get() = _nextPage

    private var _previousPage = MutableLiveData<Int>()

    val previousPage: LiveData<Int>
        get() = _previousPage


    private var _isSessionActive = MutableLiveData<StateUi<Boolean>>()
    val isSessionActive: LiveData<StateUi<Boolean>>
        get() = _isSessionActive

    private var _loginUser = MutableLiveData<StateUi<AuthResult>>()

    val loginUser: LiveData<StateUi<AuthResult>>
        get() = _loginUser

    private var _registerUser = MutableLiveData<StateUi<AuthResult>>()

    val registerUser: LiveData<StateUi<AuthResult>>
        get() = _registerUser

    private var email: String = ""

    fun actionNextPage(page: Int) {
        _nextPage.value = page
    }

    fun actionPreviousPage(page: Int) {
        _previousPage.value = page
    }


    fun isSessionActive() {
        _isSessionActive.value = StateUi.Loading
        viewModelScope.launch {
            val result = firebaseAuthRepository.isSessionActive()
            result.eitther(::handleErrorSession, ::handleSessionActive)
        }
    }

    fun login(password: String) {
        _loginUser.value = StateUi.Loading
        if (email.isNotEmpty() && password.isNotEmpty())
            viewModelScope.launch {
                val result = firebaseAuthRepository.login(email, password)
                result.eitther(::handleLoginError, ::handleLoginSuccess)
            }
    }

    fun nextStepLogin(email: String) {
        this.email = email
    }

    fun register(email: String, password: String) {
        _registerUser.value = StateUi.Loading
        viewModelScope.launch {
            val result = firebaseAuthRepository.registerUser(email, password)
            result.eitther(::handleErrorRegister, ::handleSuccessRegister)
        }
    }

    private fun handleSessionActive(isSessionActive: Boolean) {
        _isSessionActive.value = StateUi.Success(isSessionActive)
    }

    private fun handleErrorSession(error: Failure) {
        _isSessionActive.value = StateUi.Error(error)
    }

    private fun handleSuccessRegister(authResult: AuthResult) {
        _registerUser.value = StateUi.Success(authResult)
        _isSessionActive.value = StateUi.Success(true)
    }

    private fun handleErrorRegister(failure: Failure) {
        _registerUser.value = StateUi.Error(failure)
    }

    private fun handleLoginSuccess(userInfo: AuthResult) {
        _loginUser.value = StateUi.Success(userInfo)
        _isSessionActive.value = StateUi.Success(true)
    }

    private fun handleLoginError(error: Failure) {
        _loginUser.value = StateUi.Error(error)
    }
}