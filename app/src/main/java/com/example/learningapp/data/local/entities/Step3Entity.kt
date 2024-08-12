package com.example.learningapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "step3_data")
data class Step3Entity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val content: String
)