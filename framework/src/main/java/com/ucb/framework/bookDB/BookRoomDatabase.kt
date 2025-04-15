package com.ucb.framework.bookDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BookTable::class], version = 1, exportSchema = false)
abstract class BookRoomDatabase : RoomDatabase() {
    abstract fun bookDao(): IBookDAO

    companion object {
        @Volatile
        var Instance: BookRoomDatabase? = null

        fun getDatabase(context: Context): BookRoomDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context.applicationContext, BookRoomDatabase::class.java, "book_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}

