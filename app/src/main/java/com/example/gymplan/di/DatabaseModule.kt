package com.example.gymplan.di

import android.content.Context
import androidx.room.Room
import com.example.gymplan.data.db.AppDatabase
import com.example.gymplan.data.db.ExerciseDAO
import com.example.gymplan.data.db.PlanDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    fun provideExerciseDAO(appDatabase: AppDatabase): ExerciseDAO {
        return appDatabase.exerciseDAO()
    }

    @Provides
    fun providePlanDao(appDatabase: AppDatabase): PlanDao {
        return appDatabase.planDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "database-name"
        ).build()
    }



}