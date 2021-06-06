package com.itgonca.rentit.data.remote.repository

import com.google.firebase.auth.AuthResult
import com.itgonca.rentit.data.remote.auth.FirebaseAuthRemoteSource
import com.itgonca.rentit.domain.repository.FirebaseAuthRepository
import com.itgonca.rentit.utils.functional.Either
import com.itgonca.rentit.utils.functional.Failure
import javax.inject.Inject

class FirebaseAuthRepositoryImpl @Inject constructor(private val firebaseAuthRemote: FirebaseAuthRemoteSource) :
    FirebaseAuthRepository {
    override suspend fun isSessionActive(): Either<Failure, Boolean> =
        firebaseAuthRemote.isSessionActive()

    override suspend fun login(email: String, password: String): Either<Failure, AuthResult> =
        firebaseAuthRemote.login(email, password)

    override suspend fun registerUser(
        email: String,
        password: String
    ): Either<Failure, AuthResult> =
        firebaseAuthRemote.registerUser(email, password)
}