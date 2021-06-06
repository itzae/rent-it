package com.itgonca.rentit.data.remote.auth

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.itgonca.rentit.utils.extension.executCall
import com.itgonca.rentit.utils.functional.Either
import com.itgonca.rentit.utils.functional.Failure
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthRemoteSourceImpl @Inject constructor(
    private val firebaseAut: FirebaseAuth,
    private val ioDispatcher: CoroutineDispatcher
) :
    FirebaseAuthRemoteSource {

    override suspend fun isSessionActive(): Either<Failure, Boolean> = executCall(ioDispatcher) {
        firebaseAut.currentUser != null
    }

    override suspend fun login(email: String, password: String): Either<Failure, AuthResult> =
        executCall(ioDispatcher) {
            firebaseAut.signInWithEmailAndPassword(email, password).await()
        }

    override suspend fun registerUser(
        email: String,
        password: String
    ): Either<Failure, AuthResult> = executCall(ioDispatcher) {
        firebaseAut.createUserWithEmailAndPassword(email, password).await()
    }
}