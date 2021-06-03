package com.itgonca.rentit.utils.extension

import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.itgonca.rentit.utils.functional.Failure
import com.itgonca.rentit.utils.functional.FirebaseFailure

fun Throwable.parseError(): Failure =
    when (this) {
        is FirebaseAuthUserCollisionException -> FirebaseFailure.EmailExist
        is FirebaseAuthInvalidCredentialsException -> FirebaseFailure.PasswordInvalid
        is FirebaseAuthInvalidUserException -> FirebaseFailure.UserNotFound
        else -> Failure.UnexpectedFailure("Error Unknown $this")
    }