package com.example.gymplan.data

import com.example.gymplan.data.db.ExerciseDAO
import com.example.gymplan.data.db.PlanDao
import com.example.gymplan.data.dto.Exercise
import com.example.gymplan.data.dto.Plan
import com.example.gymplan.data.util.DTOEntityConverter
import com.example.gymplan.data.util.Resource
import com.example.gymplan.domain.PlanRepository
import javax.inject.Inject
import com.example.gymplan.data.dto.PlanList

class PlanRepositoryImpl @Inject constructor(val planDao: PlanDao, val exerciseDAO: ExerciseDAO ,val converter: DTOEntityConverter) : PlanRepository {
    override suspend fun getAllPlans() : Resource<PlanList> {
        return try {
            Resource.Success(PlanList(planDao.getAll().map { converter.convert(it) }))
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }

    }

    override suspend fun updatePlan(plan: Plan) {
        planDao.updatePlan(converter.convert(plan))
    }

    override suspend fun deletePlan(plan: Plan) {
        planDao.deletePlan(converter.convert(plan))
    }

    override suspend fun insertPlan(plan: Plan) {
        planDao.insertPlan(converter.convert(plan))
    }

    override suspend fun insertExercise(exercise: Exercise, planId: Int) {
        exerciseDAO.insertExercise(converter.convert(exercise, planId))
    }

    override suspend fun deleteExercise(exercise: Exercise, planId: Int) {
        exerciseDAO.deleteExercise(converter.convert(exercise, planId))
    }

    override suspend fun updateExercise(exercise: Exercise, planId: Int) {
        exerciseDAO.updateExercise(converter.convert(exercise, planId))
    }
}