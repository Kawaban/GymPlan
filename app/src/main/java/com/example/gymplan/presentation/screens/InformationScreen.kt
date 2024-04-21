package com.example.gymplan.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gymplan.data.dto.ExerciseResult
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun InformationScreen(exercises: ExerciseResult, navigator: DestinationsNavigator)
{
    LazyColumn {
        items(exercises.listOfExercise) { exercise ->
            Text(text = exercise.name, modifier = Modifier.fillMaxSize().padding(16.dp))
        }
    }
}