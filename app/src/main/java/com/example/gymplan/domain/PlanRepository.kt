package com.example.gymplan.domain

import com.example.gymplan.data.dto.Exercise
import com.example.gymplan.data.dto.Plan
import com.example.gymplan.data.dto.PlanList
import com.example.gymplan.data.util.Resource

interface PlanRepository {
    suspend fun getAllPlans(): Resource<PlanList>
    suspend fun deletePlan(plan: Plan)
    suspend fun insertPlan(plan: Plan)
    suspend fun updatePlan(plan: Plan)
    suspend fun insertExercise(exercise: Exercise, planId: Int)
    suspend fun deleteExercise(exercise: Exercise, planId: Int)
    suspend fun updateExercise(exercise: Exercise, planId: Int)

}