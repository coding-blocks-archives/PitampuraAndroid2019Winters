package com.puldroid.roomdatabase

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val db by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "user.db"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    val name: MutableLiveData<String> = MutableLiveData()
    val list = arrayListOf<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = UserAdapter(list)
        db.userDao().getAllUsers().observe(this, Observer {
            list.clear()
            list.addAll(it)
            adapter.notifyDataSetChanged()
        })

        name.observe(this, Observer {
            Log.i("LiveData", "$it")
        })
        btn.setOnClickListener {
            db.userDao().insertUser(User(editText.text.toString(), 15, "Student"))
        }

        userRv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            this.adapter = adapter
        }


    }
}
