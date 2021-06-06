package com.itgonca.rentit.di

import com.itgonca.rentit.data.remote.auth.FirebaseAuthRemoteSource
import com.itgonca.rentit.data.remote.auth.FirebaseAuthRemoteSourceImpl
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