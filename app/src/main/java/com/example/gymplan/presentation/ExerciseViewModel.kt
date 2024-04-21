package com.example.gymplan.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymplan.data.ExerciseRepository
import com.example.gymplan.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(val exerciseRepository: ExerciseRepository) : ViewModel() {

    var state by mutableStateOf(ExerciseState())
        private set

        fun getExercises(type: String, number: Int = 10) {
            viewModelScope.launch {
                state = state.copy(
                    isLoading = true,
                    error = null,
                    isReady = false
                )
                val result = exerciseRepository.getExercises(type, number)
                state = when (result) {
                    is Resource.Success -> {
                        state.copy(
                            exercise = result,
                            isLoading = false,
                            isReady = true
                        )
                    }

                    is Resource.Error -> {
                        state.copy(
                            error = result.message,
                            isLoading = false,
                            isReady = true
                        )
                    }
                }
            }

        }

        fun resetState() {
            state = ExerciseState()
        }
}
