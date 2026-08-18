package com.example.educationpath.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "paths")
data class Path(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String?,
    val creationDate: Long = System.currentTimeMillis()
)