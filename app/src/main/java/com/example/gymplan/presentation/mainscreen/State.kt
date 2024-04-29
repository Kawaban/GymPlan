package com.example.gymplan.presentation.mainscreen

import com.example.gymplan.data.util.Resource

data class State<T>(
    val obj: Resource<T>? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isReady: Boolean = false
)