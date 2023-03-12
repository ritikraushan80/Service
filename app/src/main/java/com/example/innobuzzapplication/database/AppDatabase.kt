package com.example.innobuzzapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.innobuzzapplication.database.entities.Content

@Database(
    entities = [
        Content::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun contentDao(): ContentDao

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): AppDatabase {
            if (instance == null)
                instance = Room.databaseBuilder(
                    ctx.applicationContext, AppDatabase::class.java,
                    "innobuzz_database"
                )
                    .allowMainThreadQueries()
                    .build()

            return instance!!
        }
    }
}