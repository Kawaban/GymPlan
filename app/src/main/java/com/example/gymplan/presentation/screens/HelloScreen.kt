package com.example.gymplan.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gymplan.presentation.ExerciseViewModel
import com.example.gymplan.presentation.destinations.InformationScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun HelloScreen(
    navigator: DestinationsNavigator,
    viewModel: ExerciseViewModel = hiltViewModel()
) {
    if (viewModel.state.isLoading) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.padding(16.dp))
            Text("Loading...")
        }
    } else {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Hello, User!",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            )


            Spacer(modifier = Modifier.padding(16.dp))

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ExtendedButton(text = "Back", image = Icons.Default.Settings) {
                        viewModel.getExercises("back")
                    }

                    Spacer(modifier = Modifier.padding(16.dp))

                    ExtendedButton(text = "Chest", image = Icons.Default.Settings) {
                        viewModel.getExercises("chest")
                    }
                }

                Spacer(modifier = Modifier.padding(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ExtendedButton(text = "Lower Arms", image = Icons.Default.Settings) {
                        viewModel.getExercises("lower arms")
                    }

                    Spacer(modifier = Modifier.padding(16.dp))

                    ExtendedButton(text = "Lower Legs", image = Icons.Default.Settings) {
                        viewModel.getExercises("lower legs")
                    }
                }
            }

        }
    }
    if (viewModel.state.isReady) {
        if (viewModel.state.exercise != null) {
            navigator.navigate(
                InformationScreenDestination(
                    exercises = viewModel.state.exercise?.data!!
                )
            )
        } else {
            Toast.makeText(LocalContext.current, viewModel.state.error, Toast.LENGTH_SHORT).show()

        }
        viewModel.resetState()

    }

}

@Composable
fun ExtendedButton(text: String, image: ImageVector, buttonColor: Color = Color.Blue, onClick: () -> Unit) {
    ExtendedFloatingActionButton(
        onClick = { onClick() },
        icon = { Icon(image, "Extended floating action button.") },
        text = { Text(text = text) },
        elevation = FloatingActionButtonDefaults.elevation(8.dp, 16.dp, 8.dp, 8.dp),
        containerColor = buttonColor,
        contentColor = Color.White,
        modifier = Modifier.width(150.dp)
    )
}





/*
@Preview(showBackground = true)
@Composable
fun PreviewHelloScreen() {
    HelloScreen()
}*/
