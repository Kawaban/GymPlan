package com.example.gymplan.data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Exercise(val name: String,
                    val instructions: List<String>,
                    val video: String,
                    val category: String,
                    val equipment: String,
                    val targetMuscle: String,
                    val secondaryMuscles: List<String>) : Parcelable
