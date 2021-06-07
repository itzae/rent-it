package com.itgonca.rentit.data.remote.database

import com.google.firebase.database.DataSnapshot
import kotlinx.coroutines.flow.Flow

interface FirebaseDataBaseRemoteSource {
    suspend fun getListLocations(): Flow<DataSnapshot>
}