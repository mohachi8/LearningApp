package com.example.learningapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.learningapp.data.local.dao.KeywordDao
import com.example.learningapp.data.local.entities.KeywordEntity

@Database(entities = [KeywordEntity::class], version = 1, exportSchema = false)
abstract class KeywordDatabase : RoomDatabase() {
    abstract fun keywordDao(): KeywordDao

    companion object {
        @Volatile
        private var INSTANCE: KeywordDatabase? = null

        fun getDatabase(context: Context): KeywordDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    KeywordDatabase::class.java,
                    "keyword_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}