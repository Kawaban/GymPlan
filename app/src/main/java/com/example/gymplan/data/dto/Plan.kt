package com.example.gymplan.data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date
@Parcelize
data class Plan(
    val name: String,
    val description: String,
    val lastInteractionDate: Date,
    val exercises: List<Exercise>,
    val planId: Int
):Parcelable
{
    constructor(name: String,description: String, lastInteractionDate: Date) : this(name,description, lastInteractionDate, listOf(), 0)
}
