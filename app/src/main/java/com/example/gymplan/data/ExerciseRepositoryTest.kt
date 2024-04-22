package com.example.gymplan.data

import com.example.gymplan.data.dto.Exercise
import com.example.gymplan.data.dto.ExerciseResult
import com.example.gymplan.data.util.Resource
import com.example.gymplan.domain.ExerciseRepository
import javax.inject.Inject
import javax.inject.Singleton

class ExerciseRepositoryTest @Inject constructor() : ExerciseRepository {
    override suspend fun getExercises(type: String, number: Int): Resource<ExerciseResult> {
        val exercise1: Exercise = Exercise(
            name = "alternate lateral pulldown",
            category = "back",
            equipment ="cable",
            video = "https://v2.exercisedb.io/image/I4XMjCBFhqaGoJ",
            targetMuscle = "lats",
            secondaryMuscles = listOf("biceps", "forearms", "shoulders"),
            instructions = listOf("Sit down on a pull-down machine with a wide bar attached to the top pulley.",
                "Make sure that you adjust the knee pad of the machine to fit your height.",
                "Grab the bar with the")
        )

        val exercise2: Exercise = Exercise(
            name = "alternate hammer curl",
            category = "biceps",
            equipment ="dumbbell",
            video = "https://v2.exercisedb.io/image/IhSIJPWFEJU76Q",
            targetMuscle = "biceps",
            secondaryMuscles = listOf("forearms"),
            instructions = listOf("Stand up with your torso upright and a dumbbell on each hand being held at arms length.",
                "The elbows should be close to the torso.",
                "The palms of the hands should be facing your torso.")
        )

        val exercise3: Exercise = Exercise(
            name = "alternate heel touchers",
            category = "abs",
            equipment ="none",
            video = "https://v2.exercisedb.io/image/5sokFlo35yBy59",
            targetMuscle = "abs",
            secondaryMuscles = listOf("obliques"),
            instructions = listOf("Lie on your back on the floor with your knees bent and your feet flat on the floor.",
                "Place your hands on the sides of your head.",
                "Crunch your torso forward and to the right while bringing your right knee in toward your chest and reaching your left elbow toward your right knee.")
        )
        return Resource.Success(ExerciseResult(listOf(exercise1, exercise2, exercise3)))
    }
}