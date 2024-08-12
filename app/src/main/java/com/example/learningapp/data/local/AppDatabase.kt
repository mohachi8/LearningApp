package com.example.learningapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.learningapp.data.local.dao.Step3Dao
import com.example.learningapp.data.local.entities.Step3Entity

@Database(entities = [Step3Entity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun step3Dao(): Step3Dao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "learning_app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}