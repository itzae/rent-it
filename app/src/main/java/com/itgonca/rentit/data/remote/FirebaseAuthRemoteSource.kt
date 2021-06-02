package com.itgonca.rentit.data.remote

import com.google.firebase.auth.AuthResult
import com.itgonca.rentit.utils.functional.Either
import com.itgonca.rentit.utils.functional.Failure

interface FirebaseAuthRemoteSource {
    suspend fun isSessionActive(): Either<Failure,Boolean>
    suspend fun login(email: String, password: String): Either<Failure,AuthResult>
    suspend fun registerUser(email: String,password: String) : Either<Failure,AuthResult>
}