package com.example.gymplan.data.db

import androidx.room.Embedded
import androidx.room.Relation

data class PlanWithAllExercises(
    @Embedded val plan: PlanEntity,
    @Relation(
        parentColumn = "plan_id",
        entityColumn = "plan_id"
    )
    val exercises: List<ExerciseEntity>
)
