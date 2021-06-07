package com.itgonca.rentit.domain.repository

import com.google.firebase.database.DataSnapshot
import kotlinx.coroutines.flow.Flow

interface FirebaseDatabaseRepository {
    suspend fun getListLocations(): Flow<DataSnapshot>
}