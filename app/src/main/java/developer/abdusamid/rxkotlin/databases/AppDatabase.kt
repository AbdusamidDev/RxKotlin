package developer.abdusamid.rxkotlin.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import developer.abdusamid.rxkotlin.dao.UserDao
import developer.abdusamid.rxkotlin.entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        var appDatabase: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (appDatabase == null) {
                appDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "user_db")
                    .fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
            return appDatabase!!
        }
    }
}