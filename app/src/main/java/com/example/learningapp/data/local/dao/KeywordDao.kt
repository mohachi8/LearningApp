package com.example.learningapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.learningapp.data.local.entities.KeywordEntity

@Dao
interface KeywordDao {

    @Insert
    suspend fun insertKeyword(keyword: KeywordEntity)
}