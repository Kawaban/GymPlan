package com.example.gymplan.presentation.exercisepickerscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymplan.data.dto.Exercise
import com.example.gymplan.data.dto.Plan
import com.example.gymplan.data.dto.PlanList
import com.example.gymplan.data.util.Resource
import com.example.gymplan.domain.PlanRepository
import com.example.gymplan.presentation.mainscreen.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddToPlanViewModel @Inject constructor(val planRepository: PlanRepository): ViewModel()  {
    var state by mutableStateOf(PlanState())
        private set

    var statePlan by mutableStateOf(State<PlanList>())
        private set

    fun addExercise(exercise: Exercise){
        viewModelScope.launch {
            state.plan?.planId?.let { planRepository.insertExercise(exercise, it) }
        }

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


    fun selectPlan(number: Int) {
        state = state.copy(
            plan = statePlan.obj?.data?.list?.get(number)
        )
    }

    fun selectPlan() {
        state = state.copy(

            plan = null
        )
    }

}