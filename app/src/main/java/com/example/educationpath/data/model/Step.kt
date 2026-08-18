package com.example.educationpath.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "steps",
    foreignKeys = [ForeignKey(
        entity = Path::class,
        parentColumns = ["id"],
        childColumns = ["pathId"],
        onDelete = ForeignKey.CASCADE
    )],
    primaryKeys = ["id"]
)
@Index(value = [androidx.room.Index(value = ["pathId"])], unique = true)
data class Step(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val pathId: Long,
    val title: String,
    val description: String,
    val dueDate: Long?,
    val isCompleted: Boolean = false
)