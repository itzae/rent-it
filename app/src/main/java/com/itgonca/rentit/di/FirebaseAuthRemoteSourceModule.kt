package com.itgonca.rentit.di

import com.itgonca.rentit.data.remote.FirebaseAuthRemoteSource
import com.itgonca.rentit.data.remote.FirebaseAuthRemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FirebaseAuthRemoteSourceModule {

    @Binds
    abstract fun provideFirebaseAuthRemoteSource(remoteSource: FirebaseAuthRemoteSourceImpl): FirebaseAuthRemoteSource
}