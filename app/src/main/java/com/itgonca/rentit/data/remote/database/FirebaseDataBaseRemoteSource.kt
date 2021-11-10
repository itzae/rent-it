package com.itgonca.rentit.data.remote.database

import com.google.firebase.database.DataSnapshot
import com.itgonca.rentit.utils.functional.Either
import com.itgonca.rentit.utils.functional.Failure
import kotlinx.coroutines.flow.Flow

interface FirebaseDataBaseRemoteSource {
    suspend fun getListLocations(): Flow<DataSnapshot>
    suspend fun updateFavoriteLocation(idLocation: Int, isFavorite: Boolean): Either<Failure,Void>
}