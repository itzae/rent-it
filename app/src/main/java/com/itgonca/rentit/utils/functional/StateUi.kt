package com.itgonca.rentit.utils.functional

sealed class StateUi<out T : Any> {
    object Loading : StateUi<Nothing>()
    data class Success<out T : Any>(val data: T) : StateUi<T>()
    data class Error(val failure: Failure) : StateUi<Nothing>()
}