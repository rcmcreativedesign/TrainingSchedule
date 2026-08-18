package com.example.trainingschedule.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class PathWithSteps(
    @Embedded val path: Path,
    @Relation(
        parentColumn = "id",
        entityColumn = "pathId"
    )
    val steps: List<Step>
) {
    val isComplete: Boolean
        get() = steps.isNotEmpty() && steps.all { it.isCompleted }
}
