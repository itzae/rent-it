package com.itgonca.rentit.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private var _nextPage = MutableLiveData<Int>()
    val nextPage: LiveData<Int>
        get() = _nextPage

    private var _previousPage = MutableLiveData<Int>()

    val previousPage: LiveData<Int>
        get() = _previousPage

    fun actionNextPage(page: Int) {
        _nextPage.value = page
    }

    fun actionPreviousPage(page: Int) {
        _previousPage.value = page
    }
}