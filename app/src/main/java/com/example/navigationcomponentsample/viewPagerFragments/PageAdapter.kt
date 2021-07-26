package com.example.navigationcomponentsample.viewPagerFragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> {
                MoviesFragment()
            }
            1 -> {
                CartoonsFragment()
            }
            2 -> {
                WebSeriesFragment()
            }
            else -> {
                MoviesFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Movies"
            }
            1 -> {
                return "Vegetables"
            }
            2 -> {
                return "Fruits"
            }
        }
        return super.getPageTitle(position)
    }
}