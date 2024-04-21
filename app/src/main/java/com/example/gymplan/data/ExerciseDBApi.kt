package com.example.gymplan.data

import okhttp3.OkHttpClient
import okhttp3.Request
import ru.gildor.coroutines.okhttp.await
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExerciseDBApi @Inject constructor(){
    @Throws(IOException::class)
    suspend fun getExercises(type: String, number: Int = 10): String? {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://exercisedb.p.rapidapi.com/exercises/bodyPart/" + type + "?limit=" + number)
            .get()
            .addHeader("X-RapidAPI-Key", "1120050b5emshec695034c8d3f0cp15fd46jsn60f4b164ea9c")
            .addHeader("X-RapidAPI-Host", "exercisedb.p.rapidapi.com")
            .build()


        val response = client.newCall(request).await()
        return response.body?.string()
    }
}