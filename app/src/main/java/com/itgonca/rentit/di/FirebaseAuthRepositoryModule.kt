package com.itgonca.rentit.di

import com.itgonca.rentit.domain.repository.FirebaseAuthRepository
import com.itgonca.rentit.domain.repository.FirebaseAuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FirebaseAuthRepositoryModule {
    @Binds
    abstract fun provideFirebaseAuthRepository(firebaseAuthRepositoryImpl: FirebaseAuthRepositoryImpl): FirebaseAuthRepository
}