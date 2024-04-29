package com.example.gymplan.data.util

import com.example.gymplan.data.db.ExerciseEntity
import com.example.gymplan.data.db.PlanEntity
import com.example.gymplan.data.db.PlanWithAllExercises
import com.example.gymplan.data.dto.Exercise
import com.example.gymplan.data.dto.Plan
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DTOEntityConverter @Inject constructor(){
    fun convert(plan: Plan): PlanEntity {
        return PlanEntity(plan.name,plan.description ,plan.lastInteractionDate)
    }

    fun convert(planWithAllExercises: PlanWithAllExercises): Plan {
        return Plan(planWithAllExercises.plan.name,planWithAllExercises.plan.description, planWithAllExercises.plan.lastInteractionDate, planWithAllExercises.exercises.map { convert(it) }, planWithAllExercises.plan.planId)
    }


    fun convert(exercise: Exercise, planId: Int): ExerciseEntity {
        return ExerciseEntity(exercise.name, exercise.instructions, exercise.video, exercise.category, exercise.equipment, exercise.targetMuscle, exercise.secondaryMuscles, planId)
    }

    fun convert(exerciseEntity: ExerciseEntity): Exercise {
        return Exercise(exerciseEntity.name, exerciseEntity.instructions, exerciseEntity.video, exerciseEntity.category, exerciseEntity.equipment, exerciseEntity.targetMuscle, exerciseEntity.secondaryMuscles)
    }


}