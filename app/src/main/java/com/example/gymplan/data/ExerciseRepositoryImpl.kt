package com.example.gymplan.data

import com.example.gymplan.data.dto.ExerciseResult
import com.example.gymplan.data.util.JsonToExerciseConverter
import com.example.gymplan.data.util.Resource
import com.example.gymplan.domain.ExerciseRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExerciseRepositoryImpl @Inject constructor(val ExerciseDBApi: ExerciseDBApi, val JsonToExerciseConverter: JsonToExerciseConverter) :
    ExerciseRepository {
    override suspend fun getExercises(type: String, number: Int):Resource<ExerciseResult>  {
        return try {
            val json = ExerciseDBApi.getExercises(type, number)
            val exercise = JsonToExerciseConverter.convert(json!!)
            Resource.Success(exercise)
        } catch (e: Exception) {
            Resource.Error(e.javaClass.name, null)
        }

    }

}