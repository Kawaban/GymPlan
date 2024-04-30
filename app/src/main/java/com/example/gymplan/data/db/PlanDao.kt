package com.example.gymplan.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface PlanDao {
    @Transaction
    @Query("SELECT * FROM PlanEntity")
    suspend fun getAll(): List<PlanWithAllExercises>

    @Delete
    suspend fun deletePlan(plan: PlanEntity)

    @Insert
    suspend fun insertPlan(plan: PlanEntity)

    @Update
    suspend fun updatePlan(plan: PlanEntity)

    @Query("DELETE FROM PlanEntity")
    suspend fun deleteAllPlans()


}