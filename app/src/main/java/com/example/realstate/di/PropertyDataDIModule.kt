package com.example.realstate.di

import com.example.realstate.network.apiClient.PropertyDataApiClient
import com.example.realstate.repository.PropertyDataRepository
import com.example.realstate.repository.PropertyListDataRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class PropertyDataDIModule {

    @Provides
    fun providePropertyDataApiClient(retrofit: Retrofit): PropertyDataApiClient = retrofit.create(PropertyDataApiClient::class.java)
}

@Module
@InstallIn(ViewModelComponent::class)
interface PropertyDataRepositoryModule {

    @Binds
    fun bindPropertyListDataRepository(propertyListDataRepository: PropertyListDataRepository): PropertyDataRepository
}