package com.evg.task_creation.di

import com.evg.database.domain.repository.DatabaseRepository
import com.evg.task_creation.data.repository.TaskCreationRepositoryImpl
import com.evg.task_creation.domain.repository.TaskCreationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TaskCreationModule {

    @Provides
    @Singleton
    fun provideTaskCreationRepository(
        databaseRepository: DatabaseRepository,
    ): TaskCreationRepository {
        return TaskCreationRepositoryImpl(
            databaseRepository = databaseRepository,
        )
    }
}