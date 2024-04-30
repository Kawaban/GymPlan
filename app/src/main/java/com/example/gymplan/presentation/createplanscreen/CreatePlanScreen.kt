package com.example.gymplan.presentation.createplanscreen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gymplan.presentation.util.ExtendedButton
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
@Destination
fun CreatePlanScreen(navigator: DestinationsNavigator, viewModel: AddPlanViewModel = hiltViewModel()) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var saved by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column( modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Text(text ="Create Plan", modifier = Modifier.padding(16.dp))

            TextField(value = name, onValueChange = { name = it }, label = { Text("Name") }, modifier = Modifier.padding(16.dp))

            TextField(value = description, onValueChange = { description = it }, label = { Text("Description") }, modifier = Modifier.padding(16.dp))

            ExtendedButton(text = "Create Plan", image = Icons.Default.Add, buttonColor = Color.Green) {
                viewModel.addPlan(name, description)
                saved = true
            }
            Spacer(modifier = Modifier.padding(16.dp))
        }


    }

    if(saved){
        Toast.makeText(LocalContext.current, "Plan saved", Toast.LENGTH_SHORT).show()
        saved = false
        navigator.popBackStack()
    }




}