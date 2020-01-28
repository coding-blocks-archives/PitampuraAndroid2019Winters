package com.puldroid.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bundle = Bundle()
        val fragment1 = BlankFragment()
        bundle.putString("name", "DC")
        fragment1.arguments = bundle
        val fragment2 = BlankFragment()
        bundle.putString("name", "Marvel")
        fragment2.arguments = bundle
        val fragment = BlankFragment()
        bundle.putString("name", "Marvel")
        fragment.arguments = bundle

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
