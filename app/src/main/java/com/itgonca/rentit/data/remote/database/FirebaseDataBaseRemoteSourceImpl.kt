package com.itgonca.rentit.data.remote.database

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.itgonca.rentit.utils.extension.awaitMultipleEvent
import com.itgonca.rentit.utils.extension.executCall
import com.itgonca.rentit.utils.functional.Either
import com.itgonca.rentit.utils.functional.Failure
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseDataBaseRemoteSourceImpl @Inject constructor(
    private val dbReference: DatabaseReference,
    private val ioDispatcher: CoroutineDispatcher
) : FirebaseDataBaseRemoteSource {

    override suspend fun getListLocations(): Flow<DataSnapshot> =
        dbReference.child("Locations").awaitMultipleEvent().flowOn(ioDispatcher)

    override suspend fun updateFavoriteLocation(
        idLocation: Int,
        isFavorite: Boolean
    ): Either<Failure, Void> =
        executCall(ioDispatcher) {
            dbReference.child("Locations").child(idLocation.toString()).child("favorite")
                .setValue(isFavorite).await()
        }

}