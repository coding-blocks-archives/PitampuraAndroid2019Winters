package com.puldroid.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        supportFragmentManager
//            .beginTransaction()
//            .add(R.id.container,BlankFragment())
//            .commit()
        val bundle = Bundle()
        button.setOnClickListener {
            val fragment = BlankFragment()
            bundle.putString("name" , "DC")
            fragment.arguments = bundle
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container,fragment)
                .commit()
        }
        button2.setOnClickListener {
            val fragment = BlankFragment()
            bundle.putString("name" , "Marvel")
            fragment.arguments = bundle
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container,fragment)
                .commit()
        }


    }
}
