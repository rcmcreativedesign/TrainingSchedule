package com.example.educationpath.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface StepDao {
    @Query("SELECT * FROM steps WHERE pathId = :pathId ORDER BY id ASC")
    fun getStepsForPath(pathId: Long): Flow<List<Step>>

    @Insert
    suspend fun insertStep(step: Step): Long

    @Update
    suspend fun updateStep(step: Step)

    @Query("SELECT * FROM steps WHERE id = :stepId")
    suspend fun getStepById(stepId: Long): Step?
}