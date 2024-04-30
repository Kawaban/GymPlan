package com.example.gymplan.presentation.allplansscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gymplan.data.dto.Plan
import com.example.gymplan.data.dto.PlanList
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun ShowAllPlansScreen(plans : PlanList, navigator: DestinationsNavigator, viewModel: ShowAllPlansViewModel = hiltViewModel()){
    val mutableList = remember {
        mutableStateListOf(
            *plans.list.toTypedArray()
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("All plans")
                }
            )

        },
        bottomBar = {
           FloatingActionButton(onClick = { viewModel.deletePlans() }) {
               Icon(Icons.Default.Delete, "Delete all plans")
           }
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(mutableList) { plan ->
                Card(modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()){
                    Text(text = plan.name)
                    Spacer(modifier = Modifier.padding(16.dp))
                    Text(text = plan.description)
                    Spacer(modifier = Modifier.padding(16.dp))
                    Text(text = plan.exercises.size.toString())
                }
            }
        }
    }

}