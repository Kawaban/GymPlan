package com.example.gymplan.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.gymplan.data.dto.Exercise
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlanViewModel @Inject constructor(): ViewModel()  {
    fun addExercise(exercise: Exercise){
        println("added exercise to plan: "+ exercise.name)
    }

}