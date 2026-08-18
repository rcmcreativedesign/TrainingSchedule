package com.example.educationpath.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PathDao {
    @Query("SELECT * FROM paths ORDER BY creationDate DESC")
    fun getAllPaths(): Flow<List<Path>>

    @Insert
    suspend fun insertPath(path: Path): Long // Returns the new path ID

    @Update
    suspend fun updatePath(path: Path)
}