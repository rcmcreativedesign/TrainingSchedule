package com.example.trainingschedule.data.repository

import com.example.trainingschedule.data.local.TrainingDao
import com.example.trainingschedule.data.model.Path
import com.example.trainingschedule.data.model.Step
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrainingRepository @Inject constructor(
    private val trainingDao: TrainingDao
) {
    fun getAllPaths(): Flow<List<Path>> = trainingDao.getAllPaths()

    fun getPathsWithSteps(): Flow<List<com.example.trainingschedule.data.model.PathWithSteps>> = trainingDao.getPathsWithSteps()

    fun getPathById(pathId: Long): Flow<Path?> = trainingDao.getPathById(pathId)

    fun getStepsForPath(pathId: Long): Flow<List<Step>> = trainingDao.getStepsForPath(pathId)

    suspend fun addPath(name: String, description: String): Long {
        return trainingDao.insertPath(Path(name = name, description = description))
    }

    suspend fun addStep(pathId: Long, title: String, description: String, dueDate: Long) {
        trainingDao.insertStep(
            Step(
                pathId = pathId,
                title = title,
                description = description,
                dueDate = dueDate
            )
        )
    }

    suspend fun toggleStepCompletion(step: Step) {
        trainingDao.updateStep(step.copy(isCompleted = !step.isCompleted))
    }

    suspend fun deletePath(path: Path) {
        trainingDao.deletePath(path)
    }
}
