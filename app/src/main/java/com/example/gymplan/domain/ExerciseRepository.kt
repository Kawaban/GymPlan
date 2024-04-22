package com.example.gymplan.domain

import com.example.gymplan.data.dto.ExerciseResult
import com.example.gymplan.data.util.Resource

interface ExerciseRepository {
    suspend fun getExercises(type: String, number: Int = 10): Resource<ExerciseResult>
}