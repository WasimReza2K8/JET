package com.jet.feature.search.data.datasource.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photo_table")
data class PhotoEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "local_id")
    val localId: String,
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "comments")
    val comments: Int,
    @ColumnInfo(name = "downloads")
    val downloads: Int,
    @ColumnInfo(name = "large_image_url")
    val largeImageURL: String,
    @ColumnInfo(name = "preview_url")
    val previewURL: String,
    @ColumnInfo(name = "likes")
    val likes: Int,
    @ColumnInfo(name = "tags")
    val tags: String,
    @ColumnInfo(name = "user_name")
    val user: String,
    @ColumnInfo(name = "user_id")
    val userId: Int,
    @ColumnInfo(name = "search_term")
    val searchTerm: String,
)
