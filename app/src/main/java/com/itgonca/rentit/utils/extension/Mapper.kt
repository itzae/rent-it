package com.itgonca.rentit.utils.extension

import com.itgonca.rentit.utils.functional.Either
import com.itgonca.rentit.utils.functional.Failure
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext

fun <R> R.toSuccess(): Either.Success<R> {
    return Either.Success(this)
}

fun <L> L.toError(): Either.Error<L> {
    return Either.Error(this)
}

suspend inline fun <T> executCall(
    ioDispatcher: CoroutineDispatcher,
    crossinline retrofitCall: suspend () -> T
): Either<Failure, T> {
    return withContext(ioDispatcher) {
        try {
            return@withContext retrofitCall().toSuccess()
        } catch (e: Exception) {
            return@withContext e.parseError().toError()
        }
    }
}

