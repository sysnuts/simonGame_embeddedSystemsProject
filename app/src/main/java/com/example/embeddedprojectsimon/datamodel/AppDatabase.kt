package com.example.embeddedprojectsimon.datamodel

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MatchResultEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun matchResultDao(): MatchResultDao
}

