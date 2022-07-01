package com.playtogether_android.app.presentation.ui.thunder

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ThunderTabViewPagerAdapter(private val fragment: FragmentActivity) :
    FragmentStateAdapter(fragment) {
    val fragments = mutableListOf<Fragment>()

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}