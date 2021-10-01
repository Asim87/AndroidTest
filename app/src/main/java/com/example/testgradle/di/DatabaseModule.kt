package com.example.testgradle.di

import android.content.Context
import androidx.room.Room
import com.example.testgradle.db.EmployeeDao
import com.example.testgradle.db.EmployeeDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideEmployeeDao(appDatabase: EmployeeDb): EmployeeDao {
        return appDatabase.getEmployeeDao()
    }

    @Provides
    @Singleton
    fun provideEmployeeDatabase(@ApplicationContext appContext: Context): EmployeeDb {
        return Room.databaseBuilder(
            appContext,
            EmployeeDb::class.java,
            "petar_app.db"
        ).fallbackToDestructiveMigration().build()
    }
}