package com.puldroid.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room

class MainActivity : AppCompatActivity() {

    val db by lazy {
        Room.databaseBuilder(this,
            AppDatabase::class.java,"user.db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db.userDao().insertUser(User("Pulkit",5,"Mentor"))
    }
}
