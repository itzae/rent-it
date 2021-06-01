package com.itgonca.rentit.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ScreensPagerAdapter(
    listFragments: ArrayList<Fragment>,
    fm: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fm, lifecycle) {

    val fragmentList: ArrayList<Fragment> = listFragments

    override fun getItemCount(): Int = fragmentList.size


    override fun createFragment(position: Int): Fragment = fragmentList[position]
}