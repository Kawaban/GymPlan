package com.example.gymplan.presentation.mainscreen


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gymplan.data.dto.PlanList
import com.example.gymplan.presentation.destinations.InformationScreenDestination
import com.example.gymplan.presentation.destinations.ShowAllPlansScreenDestination
import com.example.gymplan.presentation.util.ExtendedButton
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun HelloScreen(
    navigator: DestinationsNavigator,
    viewModel: ExerciseViewModel = hiltViewModel(),
) {
    viewModel.getAllPlans()

   /* if (viewModel.statePlan.isLoading) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.padding(16.dp))
            Text("Loading...")
        }
        return
    }*/

    if (viewModel.stateExercise.isLoading) {
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

            Spacer(modifier = Modifier.padding(50.dp))

            Button(onClick = {
                navigator.navigate(
                    ShowAllPlansScreenDestination(
                        plans = viewModel.statePlan.obj?.data!!
                    )
                )
            }) {
                Text("Show all plans")
            }

        }
    }
    if (viewModel.stateExercise.isReady) {
        if (viewModel.stateExercise.obj != null) {
            navigator.navigate(
                InformationScreenDestination(
                    exercises = viewModel.stateExercise.obj?.data!!,
                )
            )
        } else {
            if (viewModel.stateExercise.error != null)
                Toast.makeText(LocalContext.current, viewModel.stateExercise.error, Toast.LENGTH_SHORT).show()
        }
        viewModel.resetStates()

    }


}








