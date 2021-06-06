package com.itgonca.rentit.utils.extension

import android.util.Log
import com.google.firebase.FirebaseException
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.itgonca.rentit.utils.functional.Failure
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

suspend fun DatabaseReference.awaitSingleEvent(): DataSnapshot? =
    suspendCancellableCoroutine { continuation ->
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    Log.d("TAG", "onDataChange: ${snapshot}")
                    continuation.resume(snapshot)
                } catch (exception: Exception) {
                    continuation.resumeWithException(exception)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                val exception = when (error.toException()) {
                    is FirebaseException -> error.toException()
                    else -> Exception("The Firebase call for reference $this was cancelled")
                }
                continuation.resumeWithException(exception)
            }
        }
        continuation.invokeOnCancellation { this.removeEventListener(listener) }
        this.addListenerForSingleValueEvent(listener)
    }

suspend fun DatabaseReference.awaitMultipleEvent(): Flow<DataSnapshot> = callbackFlow {
    val valueEventListener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) = this@callbackFlow.sendBlocking(snapshot.toSuccess())
        override fun onCancelled(error: DatabaseError) = run {
            val exception = when(error.toException()) {
                is DatabaseError -> error.toError() as Failure
                else -> Exception("The Firebase call for reference $this was cancelled").parseError().toError()
            }
            this@callbackFlow.sendBlocking(exception)
        }
    }
    addValueEventListener(valueEventListener)
    awaitClose { removeEventListener(valueEventListener) }
}