package com.puldroid.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*

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
        val list = db.userDao().getAllUsers()
        val adapter = UserAdapter(list as ArrayList<User>)
        userRv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            this.adapter =  adapter
        }


//        db.userDao().insertUser(User("Archit",15,"Student"))
    }
}
