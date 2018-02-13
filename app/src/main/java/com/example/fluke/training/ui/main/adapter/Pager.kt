package com.example.fluke.training.ui.main.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.fluke.training.ui.main.HomeActivity
import com.example.fluke.training.ui.main.discover.DiscoverFragment
import com.example.fluke.training.ui.main.movie.HomeMovieFragment
import com.example.fluke.training.ui.main.television.HomeTvFragment

class Pager(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment = when (position) {
        0 -> HomeMovieFragment()
        1 -> HomeTvFragment()
        else -> DiscoverFragment()}

        override fun getCount() : Int = 3

    override fun getPageTitle(position: Int): CharSequence? = when(position) {
        0 -> HomeActivity.tab1
        1 -> HomeActivity.tab2
        else -> HomeActivity.tab3
    }
}