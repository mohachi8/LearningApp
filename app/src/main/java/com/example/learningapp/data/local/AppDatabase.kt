//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.example.learningapp.data.local.dao.KeywordDao
//import com.example.learningapp.data.local.entities.KeywordEntity
//
//@Database(entities = [KeywordEntity::class], version = 1)
//abstract class AppDatabase : RoomDatabase() {
//    abstract fun keywordDao(): KeywordDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: AppDatabase? = null
//
//        fun getDatabase(context: Context): AppDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    AppDatabase::class.java,
//                    "keyword_database"
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
//}