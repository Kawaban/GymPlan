package com.example.gymplan.presentation.exercisepickerscreen

import Spinner
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.gymplan.data.dto.Exercise
import com.example.gymplan.data.dto.ExerciseResult
import com.example.gymplan.data.dto.PlanList
import com.example.gymplan.presentation.destinations.CreatePlanScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay

@Destination
@Composable
fun InformationScreen(exercises: ExerciseResult, navigator: DestinationsNavigator, viewModel: AddToPlanViewModel = hiltViewModel())
{
    viewModel.getAllPlans()

    if (viewModel.statePlan.isLoading) {
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

    }
    else {


        val mutableList = remember {
            mutableStateListOf(
                *exercises.listOfExercise.toTypedArray()
            )
        }

        var listNames = remember {
            mutableStateListOf(
                *viewModel.statePlan.obj?.data?.list?.map { it.name }!!.toTypedArray()
            )
        }
        if (listNames.size == 0)
            listNames += "No plans available"
        else
            viewModel.selectPlan(0)

        listNames += "Create new plan"




        Scaffold(
            topBar = {
                Spinner(listNames = listNames) {
                    if (it == "Create new plan") {
                        navigator.navigate(CreatePlanScreenDestination())
                    } else if (it != "No plans available") {
                        viewModel.selectPlan(listNames.indexOf(it))
                    } else {
                        viewModel.selectPlan()
                    }

                }
            }
        ) { innerPadding ->
            LazyColumn(modifier = Modifier.padding(innerPadding)) {
                items(mutableList) { exercise ->
                    SwipeToDeleteContainer(
                        isPossibleToDelete = viewModel.state.plan != null,
                        item = exercise,
                        onDelete = { onDelete(viewModel, mutableList, it) }) {
                        ExerciseCard(exercise)
                        Spacer(modifier = Modifier.padding(16.dp))
                    }
                }
            }

        }

    }


}

fun onDelete(viewModel: AddToPlanViewModel, mutableList: MutableList<Exercise>, it: Exercise)
{
    if(viewModel.state.plan!=null) {
        viewModel.addExercise(it)
        mutableList-=it
    }
}


@Composable
fun ExerciseCard(exercise:Exercise)
{
    Card(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()) {
        Text(text = exercise.name, modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(), textAlign = TextAlign.Center, fontSize = 20.sp)

        Text(text ="Category: " + exercise.category, modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(), textAlign = TextAlign.Left, fontSize = 16.sp)

        Text(text ="Equipment: " + exercise.equipment, modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(), textAlign = TextAlign.Left, fontSize = 16.sp)

        Text(text ="Target Muscle: " + exercise.targetMuscle, modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(), textAlign = TextAlign.Left, fontSize = 16.sp)

        var secondaryMuscles = ""
        for(i in 0 until exercise.secondaryMuscles.size)
        {
            secondaryMuscles += exercise.secondaryMuscles[i] + ", "
        }
        secondaryMuscles = secondaryMuscles.dropLast(2)

        Text(text = "Secondary Muscles: $secondaryMuscles", modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(), textAlign = TextAlign.Left, fontSize = 16.sp)


        Text(text ="Instructions: ", modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(), textAlign = TextAlign.Left, fontSize = 16.sp)

        for(i in 0 until exercise.instructions.size)
        {
            val index = i + 1
            Text(text ="$index. " + exercise.instructions[i], modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(), textAlign = TextAlign.Left, fontSize = 16.sp)
        }

        GifImageFromUrl(gifUrl = exercise.video, modifier = Modifier.fillMaxWidth())
    }
}


@Composable
fun GifImageFromUrl(gifUrl: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(gifUrl)
            .decoderFactory(ImageDecoderDecoder.Factory())
            .build(),
        contentDescription = null,
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .padding(16.dp)
            .then(modifier),
        contentScale = ContentScale.Crop
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> SwipeToDeleteContainer(
    isPossibleToDelete: Boolean = true,
    item: T,
    onDelete: (T) -> Unit,
    animationDuration: Int = 500,
    content: @Composable (T) -> Unit
) {
    if (!isPossibleToDelete) {
        content(item)
        return
    }
    var isRemoved by remember {
        mutableStateOf(false)
    }
    val state = rememberDismissState(
        confirmValueChange = { value ->
            if (value == DismissValue.DismissedToEnd) {
                isRemoved = true
                true
            } else {
                false
            }
        }
    )

    LaunchedEffect(key1 = isRemoved) {
        if(isRemoved) {
            delay(animationDuration.toLong())
            onDelete(item)
        }
    }

    AnimatedVisibility(
        visible = !isRemoved,
        exit = shrinkVertically(
            animationSpec = tween(durationMillis = animationDuration),
            shrinkTowards = Alignment.Top
        ) + fadeOut()
    ) {
        SwipeToDismiss(
            state = state,
            background = {
                DeleteBackground(swipeDismissState = state)
            },
            dismissContent = { content(item) },
            directions = setOf(DismissDirection.StartToEnd)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteBackground(
    swipeDismissState: DismissState
) {
    val color = if (swipeDismissState.dismissDirection == DismissDirection.StartToEnd) {
        Color.Green
    } else Color.Transparent

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .padding(16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null,
            tint = Color.White
        )
    }
}




