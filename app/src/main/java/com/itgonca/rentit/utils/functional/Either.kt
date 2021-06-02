package com.itgonca.rentit.utils.functional

sealed class Either<out L, out R> {

    data class Error<out L>(val error: L) : Either<L, Nothing>()

    data class Success<out R>(val success: R) : Either<Nothing, R>()

    val isSuccess get() = this is Success<R>
    val isError get() = this is Error<L>

    fun eitther(fnL: (L) -> Any, fnR: (R) -> Any): Any =
        when (this) {
            is Error -> fnL(error)
            is Success -> fnR(success)
        }
}