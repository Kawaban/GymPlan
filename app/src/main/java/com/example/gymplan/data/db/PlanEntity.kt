package com.example.gymplan.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class PlanEntity(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "last_interaction_date") val lastInteractionDate: Date
)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "plan_id")
    public var planId: Int = 0
}