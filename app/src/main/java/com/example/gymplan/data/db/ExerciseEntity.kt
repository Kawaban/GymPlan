package com.example.gymplan.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise")
data class ExerciseEntity(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "instructions") val instructions: List<String>,
    @ColumnInfo(name = "video") val video: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "equipment") val equipment: String,
    @ColumnInfo(name = "target_muscle") val targetMuscle: String,
    @ColumnInfo(name = "secondary_muscles") val secondaryMuscles: List<String>,
    @ColumnInfo(name = "plan_id") val planId: Int
)
{
    @PrimaryKey(autoGenerate = true)
    public var uid: Int = 0

}

