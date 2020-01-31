package com.puldroid.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment1 = BlankFragment()
        fragment1.arguments = Bundle().apply { putString("name", "DC") }
        val fragment2 = BlankFragment()
        fragment2.arguments = Bundle().apply { putString("name", "M") }
        val fragment = BlankFragment()
        fragment.arguments = Bundle().apply { putString("name", "MARVEL") }

        val pagerAdapter = ViewPagerAdapter(supportFragmentManager)
        pagerAdapter.apply {
            add(fragment)
            add(fragment1)
            add(fragment2)
            add(DcFragment())
        }
        viewPager.adapter = pagerAdapter
//        supportFragmentManager
//            .beginTransaction()
//            .add(R.id.container,BlankFragment())
//            .commit()
//        val bundle = Bundle()
//        button.setOnClickListener {
//            val fragment = BlankFragment()
//            bundle.putString("name" , "DC")
//            fragment.arguments = bundle
//            supportFragmentManager
//                .beginTransaction()
//                .replace(R.id.container,fragment)
//                .commit()
//        }
//        button2.setOnClickListener {
//            val fragment = BlankFragment()
//            bundle.putString("name" , "Marvel")
//            fragment.arguments = bundle
//            supportFragmentManager
//                .beginTransaction()
//                .replace(R.id.container,fragment)
//                .commit()
//        }


    }
}
