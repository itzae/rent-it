package com.itgonca.rentit.utils.functional

sealed class FirebaseFailure : Failure.CustomFailure() {
    object EmailExist : Failure.CustomFailure()
    object PasswordInvalid : Failure.CustomFailure()
    object UserNotFound : Failure.CustomFailure()
}