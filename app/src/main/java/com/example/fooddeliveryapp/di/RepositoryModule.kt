package com.example.fooddeliveryapp.di

import com.example.fooddeliveryapp.authentication.login.data.repository.FirebaseAuthRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesAuthRepository(auth: FirebaseAuth):FirebaseAuthRepository = FirebaseAuthRepository(auth)
}