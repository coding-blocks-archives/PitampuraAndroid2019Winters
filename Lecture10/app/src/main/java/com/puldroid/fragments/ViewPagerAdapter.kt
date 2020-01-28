package com.puldroid.fragments

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter

/**
 * @author aggarwalpulkit596
 */

class ViewPagerAdapter(fm:FragmentManager) : FragmentStatePagerAdapter(fm){

    private val fragments = ArrayList<Fragment>()

    fun add(fragment: Fragment){
        fragments.add(fragment)
    }

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

}