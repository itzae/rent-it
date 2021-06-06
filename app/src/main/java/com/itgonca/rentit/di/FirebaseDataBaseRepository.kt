package com.itgonca.rentit.di

import com.itgonca.rentit.data.remote.repository.FirebaseDatabaseRepositoryImpl
import com.itgonca.rentit.domain.repository.FirebaseDatabaseRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FirebaseDataBaseRepository {
    @Binds
    abstract fun providesRepositoryInstance(repository: FirebaseDatabaseRepositoryImpl): FirebaseDatabaseRepository
}