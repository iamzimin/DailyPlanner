package com.evg.todo_list.di

import com.evg.database.domain.repository.DatabaseRepository
import com.evg.todo_list.data.repository.ToDoListRepositoryImpl
import com.evg.todo_list.domain.repository.ToDoListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ToDoListModule {

    @Provides
    @Singleton
    fun provideToDoListRepository(
        databaseRepository: DatabaseRepository,
    ): ToDoListRepository {
        return ToDoListRepositoryImpl(
            databaseRepository = databaseRepository,
        )
    }
}