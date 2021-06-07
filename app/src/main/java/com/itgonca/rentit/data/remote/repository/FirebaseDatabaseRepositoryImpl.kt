package com.itgonca.rentit.data.remote.repository

import com.google.firebase.database.DataSnapshot
import com.itgonca.rentit.data.remote.database.FirebaseDataBaseRemoteSource
import com.itgonca.rentit.domain.repository.FirebaseDatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FirebaseDatabaseRepositoryImpl @Inject constructor(private val databaseRemote: FirebaseDataBaseRemoteSource) :
    FirebaseDatabaseRepository {

    override suspend fun getListLocations(): Flow<DataSnapshot> =
        databaseRemote.getListLocations()

}