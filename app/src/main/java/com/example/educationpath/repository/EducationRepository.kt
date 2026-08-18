package com.example.educationpath.repository

import com.example.educationpath.data.dao.PathDao
import com.example.educationpath.data.dao.StepDao
import com.example.educationpath.data.model.Path
import com.example.educationpath.data.model.Step
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EducationRepository @Inject constructor(
    private val pathDao: PathDao,
    private val stepDao: StepDao
) {
    // Path Operations
    fun getAllPaths(): Flow<List<Path>> = pathDao.getAllPaths()

    suspend fun insertPath(path: Path): Long = pathDao.insertPath(path)

    // Step Operations
    fun getStepsForPath(pathId: Long): Flow<List<Step>> = stepDao.getStepsForPath(pathId)

    suspend fun insertStep(step: Step): Long = stepDao.insertStep(step)

    suspend fun updateStep(step: Step): Long = stepDao.updateStep(step)
}