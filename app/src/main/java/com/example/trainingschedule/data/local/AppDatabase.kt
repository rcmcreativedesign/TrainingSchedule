package com.example.trainingschedule.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.trainingschedule.data.model.Path
import com.example.trainingschedule.data.model.Step

@Database(entities = [Path::class, Step::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun trainingDao(): TrainingDao
}
