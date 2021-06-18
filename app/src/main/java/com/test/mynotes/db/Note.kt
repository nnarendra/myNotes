package com.test.mynotes.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
public class Note(
    val tittle: String?,
    val description: String?,
    var liked_flag: Boolean?,
    val priority: Int?,
    val created_at: String?,
    val updated_at: String?


) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}