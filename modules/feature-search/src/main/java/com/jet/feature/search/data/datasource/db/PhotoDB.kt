package com.jet.feature.search.data.datasource.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jet.feature.search.data.datasource.db.dao.PhotoDao
import com.jet.feature.search.data.datasource.db.entity.PhotoEntity

@Database(
    entities = [PhotoEntity::class],
    version = 1,
    exportSchema = false,
)

abstract class PhotoDB : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
}
