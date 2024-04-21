package com.example.gymplan.data.util

import com.example.gymplan.data.dto.Exercise
import com.example.gymplan.data.dto.ExerciseResult
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JsonToExerciseConverter @Inject constructor(){
    @Throws(JSONException::class)
    fun convert(json: String): ExerciseResult {
        val exercises = mutableListOf<Exercise>()
        val jsonArray = JSONArray(json)
        for (i in 0 until jsonArray.length()) {
            val item = jsonArray.getJSONObject(i)
            exercises.add(convertItem(item))
        }
        return ExerciseResult(exercises)
    }
    @Throws(JSONException::class)
    private fun convertItem(json:JSONObject ): Exercise {
        val name = json.getString("name")
        val instructions = json.getJSONArray("instructions").toList()
        val video = json.getString("gifUrl")
        val category = json.getString("bodyPart")
        val equipment = json.getString("equipment")
        val targetMuscle = json.getString("target")
        val secondaryMuscles = json.getJSONArray("secondaryMuscles").toList()
        return Exercise(name, instructions, video, category, equipment, targetMuscle, secondaryMuscles)
    }
    @Throws(JSONException::class)
    private fun JSONArray.toList(): List<String> {
        val list = mutableListOf<String>()
        for (i in 0 until this.length()) {
            list.add(this.getString(i))
        }
        return list
    }
}

