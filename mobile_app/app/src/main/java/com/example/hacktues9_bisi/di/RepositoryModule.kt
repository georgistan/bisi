package com.example.hacktues9_bisi.di

import com.example.hacktues9_bisi.data.EntityMapper
import com.example.hacktues9_bisi.data.server.CustomerService
import com.example.hacktues9_bisi.repository.CustomersRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(
        customerService: CustomerService,
        mapper: EntityMapper
    ): CustomersRepositoryImpl {
        return CustomersRepositoryImpl(customerService, mapper)
    }
}