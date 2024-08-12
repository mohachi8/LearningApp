package com.example.learningapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.learningapp.data.local.entities.Step3Entity

@Dao
interface Step3Dao {

    @Insert
    suspend fun insertStep3Data(step3Entity: Step3Entity)
}