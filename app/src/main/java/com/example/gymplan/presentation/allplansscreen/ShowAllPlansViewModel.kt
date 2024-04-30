package com.example.gymplan.presentation.allplansscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymplan.domain.PlanRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowAllPlansViewModel @Inject constructor(val planRepository: PlanRepository) : ViewModel() {
    fun deletePlans(){
        viewModelScope.launch {
            planRepository.deleteAllPlans()
        }
    }
}