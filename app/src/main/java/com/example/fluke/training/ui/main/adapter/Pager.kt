package com.example.fluke.training.ui.main.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.fluke.training.ui.main.movie.HomeMovieFragment
import com.example.fluke.training.ui.main.television.HomeTvFragment

class Pager(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment = when (position) {
        0 -> HomeMovieFragment()
        else -> HomeTvFragment()}

        override fun getCount() : Int = 2

    override fun getPageTitle(position: Int): CharSequence? = when(position) {
        0 -> "Movie"
        else -> "Television"
    }
    }