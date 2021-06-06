package com.itgonca.rentit.domain.repository

import com.google.firebase.database.DataSnapshot
import com.itgonca.rentit.utils.functional.Either
import com.itgonca.rentit.utils.functional.Failure
import kotlinx.coroutines.flow.Flow

interface FirebaseDatabaseRepository {
    suspend fun getListLocations(): Either<Failure, Flow<DataSnapshot>>
}