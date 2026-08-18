package com.example.trainingschedule.data.model

import org.junit.Assert.*
import org.junit.Test

class PathWithStepsTest {

    @Test
    fun `isComplete returns true when all steps are completed`() {
        val path = Path(name = "Test", description = "Test")
        val steps = listOf(
            Step(pathId = 1, title = "Step 1", description = "", dueDate = 0, isCompleted = true),
            Step(pathId = 1, title = "Step 2", description = "", dueDate = 0, isCompleted = true)
        )
        val pathWithSteps = PathWithSteps(path, steps)
        assertTrue(pathWithSteps.isComplete)
    }

    @Test
    fun `isComplete returns false when some steps are not completed`() {
        val path = Path(name = "Test", description = "Test")
        val steps = listOf(
            Step(pathId = 1, title = "Step 1", description = "", dueDate = 0, isCompleted = true),
            Step(pathId = 1, title = "Step 2", description = "", dueDate = 0, isCompleted = false)
        )
        val pathWithSteps = PathWithSteps(path, steps)
        assertFalse(pathWithSteps.isComplete)
    }

    @Test
    fun `isComplete returns false when there are no steps`() {
        val path = Path(name = "Test", description = "Test")
        val steps = emptyList<Step>()
        val pathWithSteps = PathWithSteps(path, steps)
        assertFalse(pathWithSteps.isComplete)
    }
}
