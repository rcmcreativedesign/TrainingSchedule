package com.example.trainingschedule.data.local

import androidx.room.*
import com.example.trainingschedule.data.model.Path
import com.example.trainingschedule.data.model.Step
import kotlinx.coroutines.flow.Flow

@Dao
interface TrainingDao {
    @Query("SELECT * FROM paths")
    fun getAllPaths(): Flow<List<Path>>

    @Transaction
    @Query("SELECT * FROM paths")
    fun getPathsWithSteps(): Flow<List<PathWithSteps>>

    @Query("SELECT * FROM paths WHERE id = :pathId")
    fun getPathById(pathId: Long): Flow<Path?>

    @Query("SELECT * FROM steps WHERE pathId = :pathId")
    fun getStepsForPath(pathId: Long): Flow<List<Step>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPath(path: Path): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStep(step: Step): Long

    @Update
    suspend fun updateStep(step: Step)

    @Delete
    suspend fun deletePath(path: Path)

    @Query("DELETE FROM steps WHERE pathId = :pathId")
    suspend fun deleteStepsForPath(pathId: Long)
}
