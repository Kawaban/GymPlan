package com.example.gymplan.di

import android.app.Application
import com.example.gymplan.data.ExerciseDBApi
import com.example.gymplan.data.ExerciseRepositoryImpl
import com.example.gymplan.data.ExerciseRepositoryTest
import com.example.gymplan.data.util.JsonToExerciseConverter
import com.example.gymplan.domain.ExerciseRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class ExerciseModule {
    @Binds
    abstract fun bindExerciseService(
        exerciseRepositoryImpl: ExerciseRepositoryTest
    ): ExerciseRepository
}
