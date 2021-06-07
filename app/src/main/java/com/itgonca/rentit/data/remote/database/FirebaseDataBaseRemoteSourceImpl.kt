package com.itgonca.rentit.data.remote.database

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.itgonca.rentit.utils.extension.awaitMultipleEvent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FirebaseDataBaseRemoteSourceImpl @Inject constructor(
    private val dbReference: DatabaseReference,
    private val ioDispatcher: CoroutineDispatcher
) :
    FirebaseDataBaseRemoteSource {
    override suspend fun getListLocations(): Flow<DataSnapshot> =
        dbReference.child("Locations").awaitMultipleEvent().flowOn(ioDispatcher)

}