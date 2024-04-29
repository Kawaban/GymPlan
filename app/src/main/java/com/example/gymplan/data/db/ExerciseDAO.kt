package com.example.gymplan.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ExerciseDAO {
    @Query("SELECT * FROM exercise")
    suspend fun getAll(): List<ExerciseEntity>
    @Query("SELECT * FROM exercise WHERE name = :name")
    suspend fun getExercise(name: String): ExerciseEntity
    @Insert
    suspend fun insertExercise(exercises: ExerciseEntity)
    @Delete
    suspend fun deleteExercise(exercise: ExerciseEntity)
    @Update
    suspend fun updateExercise(exercise: ExerciseEntity)
}