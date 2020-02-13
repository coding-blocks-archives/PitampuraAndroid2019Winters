package com.example.misc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    val msg = MutableLiveData<String>()
    val ms2 = MutableLiveData<String>()

    val data = MediatorLiveData<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setTitle("Toolbars")


        data.addSource(msg, Observer {
            data.value = it
        })

        data.addSource(ms2, Observer {
            data.value = it
        })
        data.observe(this, Observer {
            if(it.isNullOrEmpty()){
                Toast.makeText(this,"Button 4 Clicked",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"$it",Toast.LENGTH_SHORT).show()
            }
        })
        button.setOnClickListener {
            msg.value =  "Button 1 Clicked"

        }

        button2.setOnClickListener {
            msg.value =  "Button 2 Clicked"

        }

        button3.setOnClickListener {
            ms2.value = "Button 3 Clicked"

        }

        button4.setOnClickListener {
            ms2.value = null

        }

        fetchUser()
    }

    private fun fetchUser() {
        User.userPosts()

        User().friends
    }
}
