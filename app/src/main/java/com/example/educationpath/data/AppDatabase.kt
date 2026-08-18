package com.example.educationpath.data.model
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "path")
data class Path(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String?,
    val steps: List<Step> = emptyList() // Storing list of steps directly (potential issue)
)

@Entity(tableName = "step")
data class Step(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String?,
    val order: Int,
    val pathId: Int // Foreign key reference, though not enforced by Room schema easily
)
