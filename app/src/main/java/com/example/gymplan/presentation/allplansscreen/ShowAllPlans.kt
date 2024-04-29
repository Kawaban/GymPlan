package com.example.gymplan.presentation.allplansscreen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gymplan.data.dto.Plan
import com.example.gymplan.data.dto.PlanList
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun ShowAllPlansScreen(plans : PlanList, navigator: DestinationsNavigator){
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
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(mutableList) { plan ->
                Card(modifier = Modifier.padding(16.dp)){
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