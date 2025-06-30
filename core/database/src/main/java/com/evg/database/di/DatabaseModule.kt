package com.evg.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.evg.database.data.repository.DatabaseRepositoryImpl
import com.evg.database.data.storage.TasksDatabase
import com.evg.database.domain.repository.DatabaseRepository

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideTasksDatabase(
        @ApplicationContext context: Context,
    ) : TasksDatabase {
        return Room.databaseBuilder(
            context,
            TasksDatabase::class.java,
            TasksDatabase.DATABASE_NAME,
        ).build()
    }

    @Provides
    @Singleton
    fun provideDatabaseRepository(
        tasksDatabase: TasksDatabase,
    ): DatabaseRepository {
        return DatabaseRepositoryImpl(
            tasksDatabase = tasksDatabase,
        )
    }
}