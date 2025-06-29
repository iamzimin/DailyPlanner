package com.evg.todo_list.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.evg.todo_list.data.repository.ToDoListRepositoryImpl
import com.evg.todo_list.domain.repository.ToDoListRepository

@Module
@InstallIn(SingletonComponent::class)
object ToDoListModule {

    @Provides
    @Singleton
    fun provideToDoListRepository(
        //@ApplicationContext context: Context,
    ): ToDoListRepository {
        return ToDoListRepositoryImpl()
    }
}