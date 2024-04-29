package com.example.gymplan.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = arrayOf(ExerciseEntity::class, PlanEntity::class ), version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase(){
    abstract fun exerciseDAO(): ExerciseDAO

    abstract fun planDao(): PlanDao

}