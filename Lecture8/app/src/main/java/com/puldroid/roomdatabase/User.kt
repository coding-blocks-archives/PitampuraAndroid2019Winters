package com.puldroid.roomdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author aggarwalpulkit596
 */
@Entity
data class User(
    val name: String,
    val age: Int,
    val desc: String,
    @PrimaryKey(autoGenerate = true)
    val id: Long= 0L
)