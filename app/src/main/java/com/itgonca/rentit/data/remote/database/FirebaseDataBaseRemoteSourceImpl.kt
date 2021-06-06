package com.itgonca.rentit.data.remote.database

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.itgonca.rentit.utils.extension.*
import com.itgonca.rentit.utils.functional.Either
import com.itgonca.rentit.utils.functional.Failure
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class FirebaseDataBaseRemoteSourceImpl @Inject constructor(
    private val dbReference: DatabaseReference,
    private val ioDispatcher: CoroutineDispatcher
) :
    FirebaseDataBaseRemoteSource {
    override suspend fun getListLocations(): Either<Failure, Flow<DataSnapshot>> =
        dbReference.child("Locations").awaitMultipleEvent().catch { return@catch it.parseError().toError() }
            .collect { return@collect it.toSuccess() }

}