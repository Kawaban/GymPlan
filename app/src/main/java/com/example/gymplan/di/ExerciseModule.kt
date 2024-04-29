package com.example.gymplan.di

import com.example.gymplan.data.ExerciseRepositoryTest
import com.example.gymplan.data.PlanRepositoryImpl
import com.example.gymplan.domain.ExerciseRepository
import com.example.gymplan.domain.PlanRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class ExerciseModule {
    @Binds
    abstract fun bindExerciseService(
        exerciseRepositoryImpl: ExerciseRepositoryTest
    ): ExerciseRepository

    @Binds
    abstract fun bindPlanService(
        planRepositoryImpl: PlanRepositoryImpl
    ): PlanRepository


}
