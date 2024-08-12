package com.example.learningapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.learningapp.data.local.entities.KeywordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface KeywordDao {

    @Insert
    suspend fun insertKeyword(keyword: KeywordEntity)

    @Update
    suspend fun updateKeyword(keyword: KeywordEntity)

    @Query("SELECT * FROM keywords WHERE title = :title")
    fun getKeywordByTitle(title: String): Flow<KeywordEntity?>

    @Query("DELETE FROM keywords WHERE title = :title")
    suspend fun deleteKeywordByTitle(title: String)
}