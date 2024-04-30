package com.example.gymplan.presentation.mainscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymplan.data.dto.ExerciseResult
import com.example.gymplan.data.dto.PlanList
import com.example.gymplan.data.util.Resource
import com.example.gymplan.domain.ExerciseRepository
import com.example.gymplan.domain.PlanRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor( private val exerciseRepository: ExerciseRepository, val planRepository: PlanRepository) : ViewModel() {

    var stateExercise by mutableStateOf(State<ExerciseResult>())
        private set

    var statePlan by mutableStateOf(State<PlanList>())
        private set

        fun getExercises(type: String, number: Int = 10) {
            viewModelScope.launch {
                stateExercise  = stateExercise.copy(
                    isLoading = true,
                    error = null,
                    isReady = false
                )
                val result = exerciseRepository.getExercises(type, number)
                stateExercise = when (result) {
                    is Resource.Success -> {
                        stateExercise.copy(
                            obj = result ,
                            isLoading = false,
                            isReady = true
                        )
                    }

                    is Resource.Error -> {
                        stateExercise.copy(
                            error = result.message,
                            isLoading = false,
                            isReady = true
                        )
                    }
                }
            }

        }

        fun resetStates() {
            stateExercise = State<ExerciseResult>()
            statePlan = State<PlanList>()
        }

    fun getAllPlans() {
        viewModelScope.launch {
            statePlan = statePlan.copy(
                isLoading = true,
                error = null,
                isReady = false
            )
            val plans = planRepository.getAllPlans()
            statePlan = when (plans) {
                is Resource.Success -> {
                    statePlan.copy(
                        obj = plans,
                        isLoading = false,
                        isReady = true
                    )
                }

                is Resource.Error -> {
                    statePlan.copy(
                        error = plans.message,
                        isLoading = false,
                        isReady = true
                    )
                }
            }
        }
    }

}
