package com.example.gymplan.presentation

import com.example.gymplan.data.dto.ExerciseResult
import com.example.gymplan.data.util.Resource

data class ExerciseState(
    val exercise: Resource<ExerciseResult>? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isReady: Boolean = false
)