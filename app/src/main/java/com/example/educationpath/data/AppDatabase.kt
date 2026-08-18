package com.example.educationpath.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.educationpath.data.model.Path
import com.example.educationpath.data.model.Step

@Database(entities = [Path::class, Step::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase<AppDatabase> {
    // Abstract methods for DAOs will be added later via build tools, 
    // but defining the class structure here.
}