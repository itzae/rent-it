package com.itgonca.rentit.domain.repository

import com.google.firebase.auth.AuthResult
import com.itgonca.rentit.utils.functional.Either
import com.itgonca.rentit.utils.functional.Failure

interface FirebaseAuthRepository {
    suspend fun isSessionActive(): Either<Failure,Boolean>
    suspend fun login(email: String, password: String): Either<Failure,AuthResult>
    suspend fun registerUser(email: String, password: String): Either<Failure,AuthResult>
}