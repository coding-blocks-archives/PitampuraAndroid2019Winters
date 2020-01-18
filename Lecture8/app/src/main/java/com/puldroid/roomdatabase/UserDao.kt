package com.puldroid.roomdatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 * @author aggarwalpulkit596
 */
@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User)

    @Insert
    fun insertAllUser(users: List<User>)

    @Query("Select * From User")
    fun getAllUsers(): List<User>

    @Query("Select * From User where age > :elder")
    fun getAllUser(elder:Int):List<User>

    @Delete
    fun deleteUser(user: User)
}