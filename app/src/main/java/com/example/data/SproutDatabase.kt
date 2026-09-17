package com.example.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TransactionEntity::class], version = 1, exportSchema = false)
abstract class SproutDatabase : RoomDatabase() {
  abstract fun transactionDao(): TransactionDao

  companion object {
    @Volatile
    private var INSTANCE: SproutDatabase? = null

    fun getDatabase(context: Context): SproutDatabase {
      return INSTANCE ?: synchronized(this) {
        val instance = Room.databaseBuilder(
          context.applicationContext,
          SproutDatabase::class.java,
          "sprout_database"
        ).fallbackToDestructiveMigration().build()
        INSTANCE = instance
        instance
      }
    }
  }
}
