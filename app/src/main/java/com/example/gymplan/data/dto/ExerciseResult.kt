package com.example.gymplan.data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExerciseResult(val listOfExercise: List<Exercise>) : Parcelable
