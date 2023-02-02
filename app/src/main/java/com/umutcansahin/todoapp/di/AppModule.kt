package com.umutcansahin.todoapp.di

import android.app.Application
import androidx.room.Room
import com.umutcansahin.todoapp.utils.Constants
import com.umutcansahin.todoapp.data.local.AppDatabase
import com.umutcansahin.todoapp.data.local.AppDatabase.Migrations
import com.umutcansahin.todoapp.data.repository.ToDoRepositoryImpl
import com.umutcansahin.todoapp.domain.mapper.ToDoEntityMapper
import com.umutcansahin.todoapp.domain.repository.ToDoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            Constants.DATABASE_NAME
        ).addMigrations(Migrations.M_1_2).build()
    }

    @Provides
    @Singleton
    fun provideToDoRepository(db: AppDatabase,): ToDoRepository {
        return ToDoRepositoryImpl(db.toDoDao)
    }

    @Provides
    @Singleton
    fun provideToDoEntityMapper(): ToDoEntityMapper {
        return ToDoEntityMapper()
    }

}