package com.itgonca.rentit.di

import com.itgonca.rentit.data.remote.database.FirebaseDataBaseRemoteSource
import com.itgonca.rentit.data.remote.database.FirebaseDataBaseRemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FirebaseDatabaseRemoteSourceModule {

    @Binds
    abstract fun providerRemoteSource(remoteSourceImpl:FirebaseDataBaseRemoteSourceImpl):FirebaseDataBaseRemoteSource
}