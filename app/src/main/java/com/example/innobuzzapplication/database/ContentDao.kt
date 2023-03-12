package com.example.innobuzzapplication.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.innobuzzapplication.database.entities.Content

@Dao
interface ContentDao {
    @Query("SELECT * FROM content_table")
    fun getAll(): List<Content>

    @Query("SELECT * FROM content_table WHERE contentId IN (:contentId)")
    fun loadByIds(contentId: IntArray): Content

    @Insert
    fun insertContent(content: Content)

    @Delete
    fun delete(content: Content)
}