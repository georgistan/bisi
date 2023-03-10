package com.example.hacktues9_bisi.di

import com.example.hacktues9_bisi.data.EntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EntityMapperModule {

    @Provides
    @Singleton
    fun provideEntityMapper(): EntityMapper = EntityMapper()

}