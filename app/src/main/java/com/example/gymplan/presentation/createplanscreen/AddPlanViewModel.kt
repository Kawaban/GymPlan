package com.example.gymplan.presentation.createplanscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymplan.data.dto.Plan
import com.example.gymplan.domain.PlanRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AddPlanViewModel @Inject constructor(val planRepository: PlanRepository): ViewModel(){
        fun addPlan(name: String, description: String) {
            viewModelScope.launch {
                planRepository.insertPlan(Plan(name, description, Date.from(Instant.now())))
            }
        }
}