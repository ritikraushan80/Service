package com.example.innobuzzapplication.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "content_table")
data class Content(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var contentId: Int? = 0,
    var userID: Int? = 0,
    var title: String? = null,
    var body: String? = null,
)